/**
 *    Copyright 2009-2019 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.apache.ibatis.plugin;

import java.util.Properties;

/**
 * @author Clinton Begin
 * 拦截器的实现必须有空参构造
 * @see org.apache.ibatis.builder.xml.XMLConfigBuilder#pluginElement(org.apache.ibatis.parsing.XNode)
 * 典型示例:
 * @see com.github.pagehelper.PageInterceptor
 * 拦截器的执行点: 即查看 {@link InterceptorChain#pluginAll(java.lang.Object)} 的调用点
 * 1. 针对执行器 {@link org.apache.ibatis.executor.Executor}
 *    @see org.apache.ibatis.session.Configuration#newExecutor(org.apache.ibatis.transaction.Transaction, org.apache.ibatis.session.ExecutorType)
 *    源码: executor = (Executor) interceptorChain.pluginAll(executor);
 * 2. 针对 {@link org.apache.ibatis.executor.statement.StatementHandler}
 *    @see org.apache.ibatis.session.Configuration#newStatementHandler(org.apache.ibatis.executor.Executor, org.apache.ibatis.mapping.MappedStatement, java.lang.Object, org.apache.ibatis.session.RowBounds, org.apache.ibatis.session.ResultHandler, org.apache.ibatis.mapping.BoundSql)
 *    源码: statementHandler = (StatementHandler) interceptorChain.pluginAll(statementHandler);
 * 3. 针对 {@link org.apache.ibatis.executor.resultset.ResultSetHandler}
 *    源码: resultSetHandler = (ResultSetHandler) interceptorChain.pluginAll(resultSetHandler);
 * 4. 针对 {@link org.apache.ibatis.executor.parameter.ParameterHandler}
 *    源码: parameterHandler = (ParameterHandler) interceptorChain.pluginAll(parameterHandler);
 * 在 {@link org.apache.ibatis.plugin.InterceptorChain#pluginAll(Object)} 中实际上又是调用
 * {@link Interceptor#plugin(java.lang.Object)}
 */
public interface Interceptor {

  /**
   *
   * @param invocation
   * @return 拦截器拦截之后执行的结果
   * @throws Throwable
   */
  Object intercept(Invocation invocation) throws Throwable;

  /**
   * 动态代理, 生成代理对象
   * @param target the 被代理对象
   *               {@link org.apache.ibatis.executor.Executor}
   *               {@link org.apache.ibatis.executor.statement.StatementHandler}
   *               {@link org.apache.ibatis.executor.resultset.ResultSetHandler}
   *               {@link org.apache.ibatis.executor.parameter.ParameterHandler}
   * @return 代理对象
   */
  default Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  default void setProperties(Properties properties) {
    // NOP
  }

}
