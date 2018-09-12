package listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import util.MyWebSocket;

public class CountListener implements HttpSessionListener,ServletContextListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("sessionΩ®¡¢");
		ServletContext application=event.getSession().getServletContext();
		HttpSession session =event.getSession();
		int num=0;
		
		if(application.getAttribute("num")!=null){
			num=(Integer)application.getAttribute("num");
		}
		num++;
		application.setAttribute("num",num);
		//for(MyWebSocket item:MyWebSocket.set) {
		//	item.sendMessage(String.valueOf(num));
		//}
		MyWebSocket.sendMessageAll(String.valueOf(num));

	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		// TODO Auto-generated method stub
System.out.println("session ß–ß");
		ServletContext application=event.getSession().getServletContext();
		HttpSession session =event.getSession();
		int num=0;
		if(application.getAttribute("num")!=null){
			num=(Integer)application.getAttribute("num");
		}
		num--;
		application.setAttribute("num",num);
		//for(MyWebSocket item:MyWebSocket.set) {
		//	item.sendMessage(String.valueOf(num));
		//}
		
		MyWebSocket.sendMessageAll(String.valueOf(num));
	}

	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
