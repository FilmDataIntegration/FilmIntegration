package nju.bpnn;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class PreProcessor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//各个导演权重
	private Map<String, Double> directors;
	//各个国家权重
	private Map<String, Double> countries;
	
//	private Map<String, Double> types;
	//各个日期权重[为五一档(4 月 27 日至 5 月 10 日)、暑期档(7 月 1 日至 9 月 1 日)、国庆档(9 月 27 日至 10 月 10 日)、贺岁档(正月初一至正月十五)和其他档期共 5 类。]
//	private double[] dates;
	private int dates;

//	private double[] rates;
	
	private double[] popularities;
	
	private double[] durations;
	
	private int price_index;
	
	/**
	 * 数据顺序：导演、国家、上映年份、评分人数、片长、评分
	 * @param original_data
	 */
	public PreProcessor(Object[][] original_data){
		price_index = original_data[0].length - 1;
		
		directors = this.processEnum(original_data, 0);
		countries = this.processEnum(original_data, 1);
		dates = this.processDate(original_data);
//		types = this.processEnum(original_data, 3);
//		rates = this.processNum(original_data, 3);
		popularities = this.processNum(original_data, 3);
		durations = this.processNum(original_data, 4);		
	}
	
	public double[][] convertData(Object[][] original_data){
		int m = original_data.length;
		double[][] data = new double[m][Processor.parameter_count];
		for (int i = 0; i < m; i++) {
			data[i][0] = directors.get((String)original_data[i][0]);
			data[i][1] = countries.get((String)original_data[i][1]);
			data[i][2] = Math.log((int)original_data[i][2] - dates);
//			data[i][2] = dates[this.getDateIndex((Date)original_data[i][2])];
//			data[i][3] = types.get((String)original_data[i][3]);
//			data[i][4] = Math.log((double)original_data[i][4] - rates[0]) / rates[1];
			data[i][3] = Math.log((int)original_data[i][3] - popularities[0]) / popularities[1];
			data[i][4] = Math.log((double)original_data[i][4] - durations[0]) / durations[1];
		}
		
		return data;
	}
	
	private Map<String, Double> processEnum(Object[][] original_data, int index){
		Map<String, Double> cache = new HashMap<String, Double>();
		Map<String, Integer> films = new HashMap<String, Integer>();
		Map<String, Double> box = new HashMap<String, Double>();
		
		for (int i = 0; i < original_data.length; i++) {
			String column = (String)original_data[i][index];
			if(films.containsKey(column)){
				films.replace(column, films.get(column) + 1);
				box.replace(column,
                        box.get(column) +
						(Double)original_data[i][price_index]);
			}else{
				films.put(column, 1);
				box.put(column, (Double)original_data[i][price_index]);
			}
		}
		
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (String column : box.keySet()) {
			double bill = box.get(column) / films.get(column);
			min = Math.min(min, bill);
			max = Math.max(max, bill);
			box.replace(column, bill);
		}
		
		double fenmu = Math.log(max - min);
		for (String name : box.keySet()) {
			cache.put(name, Math.log(box.get(name) - min) / fenmu);
		}
		return cache;
	}
	
	private int processDate(Object[][] original_data){
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < original_data.length; i++) {
			min = Math.min(min, (int)original_data[i][2]);
		}
		
		return min;
		
//		double[] dates = new double[5];
//		int[] films = new int[5];
//		double[] box = new double[5];
//		
//		for (int i = 0; i < original_data.length; i++) {
//			int index = this.getDateIndex((Date)original_data[i][2]);
//			films[index] ++;
//			box[index] += (Double)original_data[i][price_index];
//		}
//		
//		double min = Double.MAX_VALUE;
//		double max = Double.MIN_VALUE;
//		for (int i = 0; i < box.length; i++) {
//			box[i] /= films[i];
//			min = Math.min(min, box[i]);
//			max = Math.max(max, box[i]);
//		}
//		
//		double fenmu = Math.log(max - min);
//		for (int i = 0; i < box.length; i++) {
//			dates[i] = Math.log(box[i] - min) / fenmu;
//		}
//		return dates;
	}
	
	private double[] processNum(Object[][] original_data, int index){
		double[] cache = new double[2];
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (int i = 0; i < original_data.length; i++) {
		    Object obj=original_data[i][index];

            double comp=0;
            if (obj instanceof Integer)
		        comp=(int)obj;
            else
                comp=(double)obj;
			min = Math.min(min, comp);
			max = Math.max(max, comp);
		}
		
		cache[0] = min;
		cache[1] = Math.log(max - min);
		return cache;
	}
	
//	private int getDateIndex(Date date){
//		int month = date.getMonth() + 1;
//		int day = date.getDayOfMonth();
//		if((month == 4 && day > 26) || (month == 5 && day < 7)){
//			//端午节
//			return 0;
//		}else if(month == 7 || month == 8){
//			//暑期档
//			return 1;
//		}else if((month == 9 && day > 26) || (month == 10 && day < 10)){
//			//国庆档
//			return 2;
//		}else if(month == 1 || month == 2){
//			//贺岁档
//			return 2;
//		}else{
//			//其他
//			return 4;
//		}
//	}
}
