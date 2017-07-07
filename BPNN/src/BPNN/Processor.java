package BPNN;

import java.io.Serializable;

public class Processor implements Serializable{
	private static final long serialVersionUID = 1L;

	private PreProcessor processor;
	
    private BP bp;
    
    private int iterator_count;
    
    public final static int parameter_count = 5;
    
    public Processor(){
    	this(new int[]{6, 2}, 0.15, 0.8, 5000);
    }
    
    /**
     * @param hidden_layer �������м��ڵ����������ж��
     * @param mobp ����ϵ��
     * @param rate ѧϰϵ��
     * @param rate ��������
     */
    public Processor(int[] hidden_layer, double mobp, double rate, int iterator_count){
    	this.iterator_count = iterator_count;
    	int[] tmp = new int[hidden_layer.length + 2];
    	tmp[0] = parameter_count;
    	for (int i = 0; i < hidden_layer.length; i++) {
			tmp[i + 1] = hidden_layer[i];
		}
    	tmp[hidden_layer.length + 1] = 1;
    	bp = new BP(tmp, mobp, rate);
    }
	/**
	 * ����˳�򣺵��ݣ�String�������ң�String������ӳ��ݣ�int��������������double����Ƭ����double�������֣�double��
	 * @param original_data
	 */
    public void train(Object[][] original_data){
    	processor = new PreProcessor(original_data);
    	System.out.println("�������Ԥ����");
        //��ʼ��������Ļ�������
        //��һ��������һ���������飬��ʾ������Ĳ�����ÿ��ڵ���������{3,10,10,10,10,2}��ʾ�������3���ڵ㣬�������2���ڵ㣬�м���4�������㣬ÿ��10���ڵ�
        //�ڶ���������ѧϰ�����������������Ƕ���ϵ��

        //�����������ݣ���Ӧ�����5ά��������
        double[][] data = processor.convertData(original_data);
        System.out.println("��ɲ���ת��");
        //����Ŀ�����ݣ���Ӧ1���������ݵķ���
        double[][] target = new double[original_data.length][1];
        for (int i = 0; i < target.length; i++) {
        	target[i][0] = (double)original_data[i][parameter_count];
		}

        //����ѵ��5000��
        for(int n=0;n<iterator_count;n++)
            for(int i=0;i<data.length;i++)
                bp.train(data[i], target[i]);

        System.out.println("���BPNNѵ��");
        
        try {
			this.predict(data);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    private double[] predict(double[][] data) throws IllegalAccessException{
    	if(processor == null){
    		throw new IllegalAccessException("��Ҫ�����ѵ���ſ���Ԥ��");
    	}
    	double[] box = new double[data.length];
    	System.out.println("��ʼ����������Ч��");
        for(int j=0;j<data.length;j++){
        	box[j] = bp.computeOut(data[j])[0];
            System.out.println("����Ʊ��Ϊ: " + box[j]);
        }
    	System.out.println("��ɼ���");
        return box;
    }
    
    public double[] predict(Object[][] original_data) throws IllegalAccessException{
    	if(processor == null){
    		throw new IllegalAccessException("��Ҫ�����ѵ���ſ���Ԥ��");
    	}
    	double[] box = new double[original_data.length];
    	System.out.println("��ʼԤ������");
        double[][] data = processor.convertData(original_data);
        for(int j=0;j<data.length;j++){
        	box[j] = bp.computeOut(data[j])[0];
            System.out.println("Ԥ��Ʊ��Ϊ: " + box[j]);
        }
    	System.out.println("���Ԥ��");
        return box;
    }
}