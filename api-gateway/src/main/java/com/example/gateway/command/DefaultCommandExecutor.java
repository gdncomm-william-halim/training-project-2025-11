package com.example.gateway.command;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DefaultCommandExecutor implements CommandExecutor, ApplicationContextAware {

  private ApplicationContext applicationContext;

  @Override
  public <R, T> T execute(Class<? extends Command<R, T>> commandClass, R request) {
    return applicationContext.getBean(commandClass)
      .execute(request);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
    this.applicationContext = applicationContext;
  }
}
