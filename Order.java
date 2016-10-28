package com.liuqi.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Order{
	
	static final int ALL = 50000; 
	static final int EACH = 100; 
	
	 static int[] arg = new int[ALL];

	
	public static void main(String[] args) {

		for (int j = 0; j < arg.length; j++) {
			arg[j] = (int)(101*Math.random());
		} 
		int[][] Initarray =  splitAry(arg, EACH);
		System.out.println();
		int[][] tempA = new int[ALL/EACH][EACH];
		for (int i = 0; i < Initarray.length; i++) {
			Sort sort = new Sort(Initarray[i]);
			System.out.println();
			sort.start();
			tempA[i] = Sort.args;
			try {
				sort.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		for (int i = 0; i < tempA.length; i++) {
			System.out.println("����tempA");
			for (int j = 0; j < EACH; j++) {
				System.out.print(tempA[i][j]+"    ");				
			}			
		}
		int FinalAray1[] = new int[2*EACH];
		DoubleSort doubleSort1 = new DoubleSort(tempA[0], EACH,tempA[1],EACH,FinalAray1);
		doubleSort1.start();
		int FinalAray2[] = new int[3*EACH];
		DoubleSort doubleSort2 = new DoubleSort(tempA[2], EACH,FinalAray1,2*EACH,FinalAray2);
		doubleSort2.start();

		int FinalAray3[] = new int[4*EACH];
		DoubleSort doubleSort3 = new DoubleSort(tempA[3], EACH,FinalAray2,3*EACH,FinalAray3);
		doubleSort3.start();

		int FinalAray4[] = new int[5*EACH];
		DoubleSort doubleSort4 = new DoubleSort(tempA[4], EACH,FinalAray3,4*EACH,FinalAray4);
		doubleSort4.start();

System.out.println();
System.out.println();
		for (int i = 0; i <FinalAray4.length; i++) {
			System.out.print(doubleSort4.arr3[i]+"    ");
			
		}



	}
	
//	public static void DoSort(int tempA[][]) {
//		
//		int FinalAray[] = new int[ALL];
//		for (int i = 0; i < tempA.length; i++) {
//			DoubleSort doubleSort = new DoubleSort(tempA[0], EACH,tempA[1],EACH,FinalAray);
//			
//		}
//		
//	}


	//�������ֳ�ָ���Ĵ�С
	private static int[][] splitAry(int[] ary, int subSize) {
		int count = ary.length % subSize == 0 ? ary.length / subSize: ary.length / subSize + 1;
		int[][] temp = new int[count][subSize];

		for (int i = 0; i < count; i++) {
			int index = i * subSize;
			int j = 0;
			while (j < subSize && index < ary.length) {
				temp[i][j] = ary[index++];
				j++;
			}
		}
		return temp;
	}



	//�Ե���������߳�����
	public static class Sort extends Thread{
		static int[] args;
		public Sort(int[] initarray) {
			this.args = initarray;
		}

		@Override
		public void run() {
			int time1 = 0,time2 = 0;
			for(int i = 0 ; i < args.length-1 ; i++){
				++time1;
				for(int j = i+1 ; j < args.length ; j++){
					++time2;
					int temp ;
					if(args[i] > args[j]){
						temp = args[j];
						args[j] = args[i];
						args[i] = temp;
					}
				}
			}

			System.out.println();
			System.out.println("��ѭ��������"+time1+"   "+"��ѭ��������"+time2);
			System.out.print("����� "+"\n");

			for(int n : args){
				System.out.print(n+"   ");

			}
		}
	}

	//�������ڲ��Ѿ��ź�����������򣬺ϲ�
	public static class DoubleSort extends Thread{
		int arr1[];
		int size1;
		int arr2[];
		int size2;
		static int arr3[];

		public DoubleSort(int arr1[], int size1, int arr2[], int size2,int arr3[]) {
			this.arr1 = arr1;
			this.arr2 = arr2;
			this.arr3 = arr3;
			this.size1 = size1;
			this.size2 = size2;
		}

		@Override
		public void run() {
			/**
			 * arr1 ��һ�����������
			 * size1 ����һ�Ĵ�С
			 * arr2 �ڶ������������
			 * size2 ������Ĵ�С
			 * arr3 ��������һ��������ϲ��Ժ����������
			 */
			int index1 = 0, index2 = 0, index3 = 0; // �ֱ��¼�������������
			while (index1 < size1 && index2 < size2) { // �����������������������ں���Χ
				// �Ƚϸ�ֵ 
				if (arr1[index1] < arr2[index2]) {
					arr3[index3++] = arr1[index1++];
				} else {
					arr3[index3++] = arr2[index2++];
				}
			}
			while (index1 < size1) { // ����2�Ѿ�����������ɣ�����1��������û�б�������
				arr3[index3++] = arr1[index1++];
			} 
			while (index2 < size2) {
				arr3[index3++] = arr2[index2++];
			}
		}
	}


}


