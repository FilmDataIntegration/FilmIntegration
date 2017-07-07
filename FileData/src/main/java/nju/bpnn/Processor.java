package nju.bpnn;

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
     * @param hidden_layer 神经网络中间层节点数，可以有多层
     * @param mobp 动量系数
     * @param rate 学习系数
     * @param rate 迭代次数
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
	 * 数据顺序：导演（String）、国家（String）、上映年份（int）、评分人数（double）、片长（double）、评分（double）
	 * @param original_data
	 */
    public void train(Object[][] original_data){
    	processor = new PreProcessor(original_data);
    	System.out.println("完成数据预处理");
        //初始化神经网络的基本配置
        //第一个参数是一个整型数组，表示神经网络的层数和每层节点数，比如{3,10,10,10,10,2}表示输入层是3个节点，输出层是2个节点，中间有4层隐含层，每层10个节点
        //第二个参数是学习步长，第三个参数是动量系数

        //设置样本数据，对应上面的5维坐标数据
        double[][] data = processor.convertData(original_data);
        System.out.println("完成参数转换");
        //设置目标数据，对应1个坐标数据的分类
        double[][] target = new double[original_data.length][1];
        for (int i = 0; i < target.length; i++) {
        	target[i][0] = (double)original_data[i][parameter_count];
		}

        //迭代训练5000次
        for(int n=0;n<iterator_count;n++)
            for(int i=0;i<data.length;i++)
                bp.train(data[i], target[i]);

        System.out.println("完成BPNN训练");
        
        try {
			this.predict(data);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
    }
    
    private double[] predict(double[][] data) throws IllegalAccessException{
    	if(processor == null){
    		throw new IllegalAccessException("需要先完成训练才可以预测");
    	}
    	double[] box = new double[data.length];
    	System.out.println("开始检验网络有效性");
        for(int j=0;j<data.length;j++){
        	box[j] = bp.computeOut(data[j])[0];
            System.out.println("检验票房为: " + box[j]);
        }
    	System.out.println("完成检验");
        return box;
    }
    
    public double[] predict(Object[][] original_data) throws IllegalAccessException{
    	if(processor == null){
    		throw new IllegalAccessException("需要先完成训练才可以预测");
    	}
    	double[] box = new double[original_data.length];
    	System.out.println("开始预测数据");
        double[][] data = processor.convertData(original_data);
        for(int j=0;j<data.length;j++){
        	box[j] = bp.computeOut(data[j])[0];
            System.out.println("预测评分为: " + box[j]);
        }
    	System.out.println("完成预测");
        return box;
    }
}
