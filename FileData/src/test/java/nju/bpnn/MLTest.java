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
    private int train_length = 15000;
    private int test_length = 100;

    @Resource
    MLFilmRepository mlFilmRepository;


    @Test
    public void testML(){
        List<MLFilm> list=mlFilmRepository.findAll();
        Object[][] objss=new Object[train_length][6];
        for (int i=0;i<train_length;i++){
            objss[i]=list.get(i).toObjArr();
        }
        Processor processor=new Processor();
        processor.train(objss, 100000);

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
            }
            System.out.println(sum);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
