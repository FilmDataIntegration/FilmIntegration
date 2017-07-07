package nju.bpnn;

import nju.FilmDataApplicationTests;
import nju.entity.MLFilm;
import nju.repository.MLFilmRepository;
import org.junit.Test;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by cxworks on 17-7-7.
 */
public class MLTest extends FilmDataApplicationTests {

    @Resource
    MLFilmRepository mlFilmRepository;


    @Test
    public void testML(){
        List<MLFilm> list=mlFilmRepository.findAll();
        Object[][] objss=new Object[list.size()][7];
        for (int i=0;i<list.size();i++){
            objss[i]=list.get(i).toObjArr();
        }
        Processor processor=new Processor();
        processor.train(objss);
        try {
            double[] pre=processor.predict(objss);
            double sum=0;

            for (int i=0;i<pre.length;i++){
                Object ob=objss[i][6];
                sum+=Math.pow((double)ob-pre[i],2);
            }
            System.out.println(sum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
