package BPNN;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class PreProcessor implements Serializable{
	private static final long serialVersionUID = 1L;
	
	//��������Ȩ��
	private Map<String, Double> directors;
	//��������Ȩ��
	private Map<String, Double> countries;
	
	private Map<String, Double> types;
	//��������Ȩ��[Ϊ��һ��(4 �� 27 ���� 5 �� 10 ��)�����ڵ�(7 �� 1 ���� 9 �� 1 ��)�����쵵(9 �� 27 ���� 10 �� 10 ��)�����굵(���³�һ������ʮ��)���������ڹ� 5 �ࡣ]
	private double[] dates;

	private double[] rates;
	
	private double[] popularities;
	
	private double[] durations;
	
	private int price_index;
	
	/**
	 * ����˳�򣺵��ݡ����ҡ���ӳʱ�䡢���͡����֡�����������Ƭ����Ʊ��
	 * @param original_data
	 */
	public PreProcessor(Object[][] original_data){
		price_index = original_data[0].length - 1;
		
		directors = this.processEnum(original_data, 0);
		countries = this.processEnum(original_data, 1);
		dates = this.processDate(original_data);
		types = this.processEnum(original_data, 3);
		rates = this.processNum(original_data, 4);
		popularities = this.processNum(original_data, 5);
		durations = this.processNum(original_data, 6);		
	}
	
	public double[][] convertData(Object[][] original_data){
		int m = original_data.length;
		double[][] data = new double[m][7];
		for (int i = 0; i < m; i++) {
			data[i][0] = directors.get((String)original_data[i][0]);
			data[i][1] = countries.get((String)original_data[i][1]);
			data[i][2] = dates[this.getDateIndex((Date)original_data[i][2])];
			data[i][3] = types.get((String)original_data[i][3]);
			data[i][4] = Math.log((double)original_data[i][4] - rates[0]) / rates[1];
			data[i][5] = Math.log((double)original_data[i][5] - popularities[0]) / popularities[1];
			data[i][6] = Math.log((double)original_data[i][6] - durations[0]) / durations[1];
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
				box.replace(column, box.get(column) + (Double)original_data[i][price_index]);
			}else{
				films.put(column, 1);
				box.replace(column, (Double)original_data[i][price_index]);
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
	
	private double[] processDate(Object[][] original_data){
		double[] dates = new double[5];
		int[] films = new int[5];
		double[] box = new double[5];
		
		for (int i = 0; i < original_data.length; i++) {
			int index = this.getDateIndex((Date)original_data[i][2]);
			films[index] ++;
			box[index] += (Double)original_data[i][price_index];
		}
		
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (int i = 0; i < box.length; i++) {
			box[i] /= films[i];
			min = Math.min(min, box[i]);
			max = Math.max(max, box[i]);
		}
		
		double fenmu = Math.log(max - min);
		for (int i = 0; i < box.length; i++) {
			dates[i] = Math.log(box[i] - min) / fenmu;
		}
		return dates;
	}
	
	private double[] processNum(Object[][] original_data, int index){
		double[] cache = new double[2];
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (int i = 0; i < original_data.length; i++) {
			min = Math.min(min, (double)original_data[i][index]);
			max = Math.max(max, (double)original_data[i][index]);
		}
		
		cache[0] = min;
		cache[1] = Math.log(max - min);
		return cache;
	}
	
	private int getDateIndex(Date date){
		int month = date.getMonth() + 1;
		int day = date.getDayOfMonth();
		if((month == 4 && day > 26) || (month == 5 && day < 7)){
			//�����
			return 0;
		}else if(month == 7 || month == 8){
			//���ڵ�
			return 1;
		}else if((month == 9 && day > 26) || (month == 10 && day < 10)){
			//���쵵
			return 2;
		}else if(month == 1 || month == 2){
			//���굵
			return 2;
		}else{
			//����
			return 4;
		}
	}
}