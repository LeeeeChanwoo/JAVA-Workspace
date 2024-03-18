package com.kh.chap01_thread.run;

import com.kh.chap01_thread.thread.Thread1;
import com.kh.chap01_thread.thread.Thread2;

public class Run {
	/*
	 * * 프로그램
	 * - 어떤 작업(프로세스)을 실행할 수 있는 파일(cpu로 자원을 할당받지 않은 상태)
	 * 
	 * * 프로세스
	 * - 현재 동작하고(실행되고) 있는 프로그램(작업을 위해 cpu로부터 자원을 할당받음)
	 * 
	 * * 스레드
	 * - 한 개의 프로세스(process)내에서 실제로 작업을 수행하는 단위
	 * - 모든 프로세스에는 한 개 이상의 스레드가 존재하여 작업을 수행한다.
	 * 
	 * ex) 
	 * - 청소(나, 1시간)+빨래(세탁기 , 2시간) +설거지(나 ,30분) +화장실청소(나 ,30분)
	 * - 무조건 있어야하는 중심이 되는 스레드 == 메인 스레드 == 나(메인스레드) == 자바 (메인메소드)
	 * - 추가로 필요한 스레드 == 서브 스레드 == main스레드에서 생성해서 실행한다.
	 * 
	 * * 멀티스레드
	 * - 한 개의 프로그램을 실행하고 그 내부적으로 여러작업(스레드)를 처리하는 것
	 * - 멀티스레드의 장점
	 *   1) 자원을 보다 효율적으로 사용할수 있다.
	 *   2) 사용자 입장에서 일처리가 빠르게 보인다.
	 *   3) 작업이 분리되어 코드가 간결해진다.
	 *   
	 *   크롬(프로세스) -> 브라우저안에서 웹쇼핑 할수있고, zoom 동영상 다운로드 받을수 있고, 인터넷 서핑도 할수있음. 
	 *  
	 *   모든 자바 어플리케이션은 Main Thread가 main메소드를 실행하면서 시작된다.★
	 */
	public static void main(String[] args) {
		// 단일 스레드
		// 항상 앞 라인의 명령문이 다 끝난후에 실행이 되었음
		
//		for(int i =1; i<=100; i++) {
//			System.out.println("작업 1 ["+i+"]");
//		}
//		for(int i =1; i<=100; i++) {
//			System.out.println("작업 2 ["+i+"]");
//		}
		// 현재 작업으로 멀티쓰레드 환경을 만들려면 필요한 쓰레드의 개수? 2(메인스레드 + 서브스레드)
		
		// 스레드 생성 방법 1 : Thread클래스를 상속받기 
		Thread1 th1 = new Thread1();
		th1.setName("스레드1");
		
		// 스레드 시작
		th1.start();
		
		// 스레드 생성 방법 2 : Runnable 인터페이스 구현하기.
		Thread th2 = new Thread(new Thread2());
		
		th2.setName("스레드2");
		
		th2.start();
		
		// 스레드 생성 방법 3 : 1회용 스레드가 필요할때 사용(익명클래스)
		// 굳이 클래스를 만들필요없이 1회용으로만 만들고자할때 사용한다.
		Thread th3 = new Thread() {
			@Override
			public void run() {
				for(int i =1; i<=100; i++) {
					System.out.println(getName()+"["+i+"]");
				}
			}
		};
		
		th3.start();
		/*
		 * 스레드의 특징
		 * 1. 메인스레드가 종료되더라도 실행중인 스레드가 하나라도 있다면 프로세스는 종료되지 않는다.
		 *    => 주종관계가 명확히 설정되어있지 않았을때 발생하는 특징. =>설정법은 곧 배울예정.
		 *    
		 * 2. 매번 결과가 다르게 보인다 => 각 스레드의 실행시점을 결정해주는 스케쥴러가 따로있기 때문.
		 * 3. 멀티스레드환경에서는 모든작업이 동시에 일어나느것처럼 보인다.  
		 *    => "한번에 하나의 스레드만 실행되고 있음"
		 *    (a스레드가 실행 -> a스레드가 멈춤 -> b스레드가 시작 -> b스레드 멈춤 -> c스레드가 실행 -> c스레드가멈춤
		 *    -> a스레드 -> 이 과정자체가 순식간에 발생하기때문에 우리눈에는 동시에 프로그램이 돌아가고 있는것처럼 보인다
		 *    => 동시성의 특징(더 빠른 반응성을 가져준다)
		 *    
		 *    이 스레드란 녀석 동시에 여러작업을하는것처럼 보이지만 사실 최종적으로 걸리는 시간은 차이가 없음.
		 *    
		 *    1. 자원자체를 논리적으로 효율적으로 쓸수있따. => 용량이 엄청큰 파일을 다운로드받는다 라고했을때
		 *    다운로드받는동안 아무것도 못하면 굉장히 사용자 입장에서 답답할것. 
		 *    2. 하드웨어적인 한계때문에 스레드는 여러개 있는게 좋음 => 단일스레드로 작업을한다면 하드웨어의수명이 금방다하게됨.
		 *    
		 *    컴퓨터의 특징
		 *    1. 프로그램이라는 녀석을 실행하기 위해서는 cpu자원을 할당받아야함. CPU == 뇌 == 뇌가있어야 행동(프로세스)을 할수있음.
		 *    
		 *    따라서 스레드가 프로그램을 실행하기 위해 CPU자원을 넘겨받음. 스케쥴러에게 자원을 빼앗김(스레드별로 실행시키기 위해서)
		 *    그렇기 때문에 명령어가 연속적으로 수행되지 못하고 , 어느부분까지 수행하고 있었는지를 스레드별로 개별적으로 기억할필요가
		 *    있음(Register에 기록).
		 *    
		 *    그리고, 개별적인 실행흐름을 보장하기위해서 각 쓰레드별로 Stack메모리영역을 할당받는다.
		 * 	
		 * 	  * Register? 스레드가 어느 명령어를 처리하고 있는지 그 주소값을 저장하고 있음.
		 * 
		 */
		System.out.println("종료됨 ㅎ");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
