package nju.controller;

import nju.bpnn.Processor;
import nju.dao.MLFilmDAO;
import nju.entity.MLFilm;
import nju.repository.MLFilmRepository;
import nju.vo.DoubanMovieListVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cxworks on 17-7-7.
 */
@RestController("/ml")
public class MLFilmController {


    @Resource
    MLFilmDAO mlFilmDAO;
    @Resource
    MLFilmRepository mlFilmRepository;
    int test_length=100;
    int train_length=15000;

    List<MLFilm> list;

    Processor processor;


    @RequestMapping(path = "all",method = RequestMethod.GET)
    public DoubanMovieListVO all(
            @RequestParam(name = "offset",defaultValue = "0") int page,
            @RequestParam(name = "limit" ,defaultValue = "10") int pageSize,
            @RequestParam(name = "sortColumn",defaultValue = "id") String sortColumn,
            @RequestParam(name = "order",defaultValue = "asc") String sortDirection){
        Sort.Direction direction=null;
        if (sortDirection.equalsIgnoreCase("asc")){
            direction= Sort.Direction.ASC;
        }else
            direction= Sort.Direction.DESC;
        Page<MLFilm> page1 = mlFilmDAO.findAll(page/pageSize,pageSize,sortColumn,direction);
        return new DoubanMovieListVO(page1.getTotalElements(),page1.getContent());
    }

    @RequestMapping(path = "q",method = RequestMethod.GET)
    public List<MLFilm> search(String key){
        return mlFilmDAO.search(key);
    }
    @RequestMapping(path = "rank/q" , method = RequestMethod.GET)
    public double rank(
            @RequestParam(name = "name",defaultValue = "") String name,
            @RequestParam(name = "releaseDate",defaultValue = "2017") int release_date,
            @RequestParam(name = "director",defaultValue = "") String director,
            @RequestParam(name = "country",defaultValue = "") String country,
            @RequestParam(name = "runtime",defaultValue = "0") int runtime){
        Object[][] objss={{director,country,release_date,0,(double)runtime,0.0}};
        try {
            double[] res=processor.predict(objss);
            return res[0];
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return 0;
        }


    }
    @RequestMapping(path = "rank/all",method = RequestMethod.GET)
    public List<MLFilm> rankAll(){
        return list;
    }


    @PostConstruct
    public void long_train(){
        list=mlFilmRepository.findAll();
        Object[][] objss=new Object[train_length][6];
        for (int i=0;i<train_length;i++){
            objss[i]=list.get(i).toObjArr();
        }
        processor=new Processor();
        processor.train(objss, 10*10000);

        objss=new Object[test_length][6];
        for (int i=0;i<test_length;i++){
            objss[i]=list.get(train_length+i).toObjArr();
        }
        try {
            double[] pre=processor.predict(objss);
            double sum=0;
            for (int i=0;i<pre.length;i++){
                Object ob=objss[i][5];
                System.out.println("预测评分：" + pre[i] + "; 实际评分：" + (double)ob);
                sum+=Math.pow((double)ob-pre[i],2);
                list.get(train_length+i).setPre(pre[i]);
            }
            list=list.subList(train_length+1,train_length+test_length);
            System.out.println(sum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
