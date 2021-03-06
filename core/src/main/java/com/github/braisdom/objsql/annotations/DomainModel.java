/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.braisdom.objsql.annotations;

import com.github.braisdom.objsql.ConnectionFactory;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The domain model is a core concept in ObjectiveSql, it holds the logic
 * and status in the application, describes the data structure of the database at the same time.<br/>
 *
 * In ObjectiveSql, The query, persistence and convenient methods for database will be generated
 * automatically base the JSR269.
 *
 * The methods generated:
 * <ul>
 *     <li>The setter and getter methods of fields</li>
 *     <li>The factory method for query and persistence</li>
 *     <li>The query methods of queryable field and model instance</li>
 *     <li>The persistence methods of model instance</li>
 *     <li>The transactional logic methods of domain</li>
 * </ul>
 *
 * @author braisdom
 * @since 1.3.1
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DomainModel {

    /**
     * Returns the table name customized. <br/>
     * It will tableize the class name as the table name by default.
     * Defines a table name by <code>DomainModel</code> annotation while it's out of the rule.
     *
     * @return a table name customized.
     *
     * @see com.github.braisdom.objsql.util.WordUtil#tableize(String)
     */
    String tableName() default "";

    /**
     * Defines a customized name of <code>DataSource</code> in multi-data source time.
     * By default, a named 'objsql-default-datasource' will be used, the application should
     * be named that way.
     *
     * @return a name of data source related to the domain model who maps a table.
     */
    String dataSource() default ConnectionFactory.DEFAULT_DATA_SOURCE_NAME;

    /**
     * It indicates the <code>DomainModel</code> has fluent method for setting field. <br>
     * Like this:
     * <pre>
     *     Model model = new Model();
     *     model.setField1(...)
     *          .setField2(...)
     *          .setField3(...);
     * </pre>
     * You can disable the feature if in special scenes.
     */
    boolean fluent() default true;

    /**
     * Changes the primary type if it is different data type in the database.
     * By default, the <code>Integer</code> is used for a primary key as the mapping of database.<br/>
     *
     * <b>Notice:</b> Only Long, Integer, Short can be as a primary key.
     */
    Class<?> primaryClass() default Long.class;

    /**
     * Customizes the column name mapped for the domain model
     */
    String primaryColumnName() default "id";

    /**
     * Customizes the field name for mapping the column

     */
    String primaryFieldName() default "id";

    /**
     * Skips the null value of field when updating a domain model if true
     * Returns false if the null value can save into the database.
     */
    boolean skipNullValueOnUpdating() default true;

    /**
     * This option indicates the most of field can save into database,
     * however the domain model can be applied into lots of different scenarios,
     * sometimes, only few field can save into database, so the option defined.
     */
    boolean allFieldsPersistent() default true;

    /**
     * Default value for primary key, in general, it is a auto increment identifier
     * at inserting.
     */
    String primaryKeyDefaultValue() default "";
}
