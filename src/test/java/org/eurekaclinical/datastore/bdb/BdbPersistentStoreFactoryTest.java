package org.eurekaclinical.datastore.bdb;

/*
 * #%L
 * JavaUtil
 * %%
 * Copyright (C) 2012 - 2013 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import org.eurekaclinical.datastore.bdb.BdbUtil;
import org.eurekaclinical.datastore.bdb.BdbPersistentStoreFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import org.arp.javautil.io.FileUtil;
import org.eurekaclinical.datastore.DataStore;

public class BdbPersistentStoreFactoryTest {

    @Test
    public void testNewInstance() throws IOException {
        String envName = BdbUtil.uniqueEnvironment("bdb-store-test", null,
                FileUtil.getTempDirectory());
        BdbPersistentStoreFactory<String, String> factory
                = new BdbPersistentStoreFactory<>(envName);
        try {
            DataStore<String, String> store = factory.newInstance("BdbTest");
            store.put("foo", "bar");
            String bar = store.get("foo");
            Assert.assertEquals("bar", bar);
            store.shutdown();
        } finally {
            factory.closeAndRemoveAllDatabaseHandles();
        }
    }
}
