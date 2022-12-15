package uz.me.marsbase.controller.component.listeners;

import uz.me.marsbase.controller.component.DataLoader;
import uz.me.marsbase.model.connection.MyConnectionPool;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        MyConnectionPool.getInstance();

        DataLoader.initialize();
        System.out.println("initializing database and adding admin");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MyConnectionPool.getInstance().destroyPool();
    }
}
