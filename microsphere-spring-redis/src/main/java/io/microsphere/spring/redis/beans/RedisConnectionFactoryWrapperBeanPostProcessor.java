/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.microsphere.spring.redis.beans;

import io.microsphere.spring.beans.factory.config.GenericBeanPostProcessorAdapter;
import io.microsphere.spring.redis.connection.RedisConnectionFactoryWrapper;
import io.microsphere.spring.redis.context.RedisContext;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * {@link RedisConnectionFactoryWrapper} {@link BeanPostProcessor}
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see RedisConnectionFactoryWrapper
 * @since 1.0.0
 */
public class RedisConnectionFactoryWrapperBeanPostProcessor extends GenericBeanPostProcessorAdapter<RedisConnectionFactory> implements BeanFactoryAware {

    public static final String BEAN_NAME = "redisConnectionFactoryWrapperBeanPostProcessor";

    private RedisContext redisContext;

    @Override
    protected RedisConnectionFactory doPostProcessAfterInitialization(RedisConnectionFactory bean, String beanName) throws BeansException {
        return new RedisConnectionFactoryWrapper(beanName, bean, redisContext);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.redisContext = RedisContext.get(beanFactory);
    }
}
