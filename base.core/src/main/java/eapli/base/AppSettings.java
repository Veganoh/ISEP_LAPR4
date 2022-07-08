/*
 * Copyright (c) 2013-2021 the original author or authors.
 *
 * MIT License
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eapli.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * the application settings.
 *
 * @author Paulo Gandra Sousa
 */
public class    AppSettings {
    private static final Logger LOGGER = LoggerFactory.getLogger(AppSettings.class);

    private static final String PROPERTIES_RESOURCE = "application.properties";
    private static final String REPOSITORY_FACTORY_KEY = "persistence.repositoryFactory";
    private static final String UI_MENU_LAYOUT_KEY = "ui.menu.layout";
    private static final String PERSISTENCE_UNIT_KEY = "persistence.persistenceUnit";
    private static final String SCHEMA_GENERATION_KEY = "javax.persistence.schema-generation.database.action";
    private static final String WAREHOUSE_PLANT_FILE = "WarehousePlantFilePath";
    private static final String SURVEY_FILE = "SurveyFilePath";
    private static final String DIGITAL_TWIN_PORT = "Digital.Twin.Port";
    private static final String AGV_MANAGER_SERVER = "AGV.Manager.Server";
    private static final String AGV_MANAGER_PORT = "AGV.Manager.Port";
    private static final String HTTP_SERVER_PORT = "HTTP.Server.Port";
    private static final String ORDER_SERVER = "Order.Server";
    private static final String ORDER_SERVER_PORT = "Order.Server.Port";
    private static final String AUTO_TASK_ASSIGNER = "AutoAGVTaskAssignerFlag";
    private static final String AGV_MANAGER_KEY_PATH = "AGV.MANAGER.SERVER.KEY";
    private static final String DIGITAL_TWIN_KEY_PATH = "DIGITAL.TWIN.SERVER.KEY";
    private static final String ORDERS_KEY_PATH = "ORDER.KEY";
    private static final String ORDERS_SERVER_KEY_PATH = "ORDER.SERVER.KEY";
    private static final String REQUESTS_KEY_PATH = "REQUEST.KEY";
    private static final String HTTP_SERVER_KEY_PATH = "HTTP.SERVER.KEY";

    private final Properties applicationProperties = new Properties();

    public AppSettings() {
        loadProperties();
    }

    private void loadProperties() {
        try (InputStream propertiesStream = this.getClass().getClassLoader()
                .getResourceAsStream(PROPERTIES_RESOURCE)) {
            if (propertiesStream != null) {
                this.applicationProperties.load(propertiesStream);
            } else {
                throw new FileNotFoundException(
                        "property file '" + PROPERTIES_RESOURCE + "' not found in the classpath");
            }
        } catch (final IOException exio) {
            setDefaultProperties();

            LOGGER.warn("Loading default properties", exio);
        }
    }

    private void setDefaultProperties() {
        this.applicationProperties.setProperty(REPOSITORY_FACTORY_KEY,
                "eapli.base.persistence.jpa.JpaRepositoryFactory");
        this.applicationProperties.setProperty(UI_MENU_LAYOUT_KEY, "horizontal");
        this.applicationProperties.setProperty(PERSISTENCE_UNIT_KEY, "eapli"
                + ".base");
        this.applicationProperties.setProperty(WAREHOUSE_PLANT_FILE, "configfiles/warehouse1.json");
        this.applicationProperties.setProperty(SURVEY_FILE, "configfiles/questionnaire.json");
    }

    public Boolean isMenuLayoutHorizontal() {
        return "horizontal"
                .equalsIgnoreCase(this.applicationProperties.getProperty(UI_MENU_LAYOUT_KEY));
    }

    public String getPersistenceUnitName() {
        return this.applicationProperties.getProperty(PERSISTENCE_UNIT_KEY);
    }

    public String getRepositoryFactory() {
        return this.applicationProperties.getProperty(REPOSITORY_FACTORY_KEY);
    }

    public String getWarehousePlantFilePath() {
        return this.applicationProperties.getProperty(WAREHOUSE_PLANT_FILE);
    }

    public String getSurveyFilePath(){
        return this.applicationProperties.getProperty(SURVEY_FILE);
    }

    public String getOrdersKeyPath(){ return this.applicationProperties.getProperty(ORDERS_KEY_PATH);}

    public String getAgvManagerKeyPath(){ return this.applicationProperties.getProperty(AGV_MANAGER_KEY_PATH);}

    public String getDigitalTwinKeyPath(){ return this.applicationProperties.getProperty(DIGITAL_TWIN_KEY_PATH);}

    public String getOrdersServerKeyPath(){ return this.applicationProperties.getProperty(ORDERS_SERVER_KEY_PATH);}

    public String getRequestsKeyPath(){ return this.applicationProperties.getProperty(REQUESTS_KEY_PATH);}

    public String getHttpServerKeyPath(){ return this.applicationProperties.getProperty(HTTP_SERVER_KEY_PATH);}

    public int getDigitalTwinPort() {
        return Integer.parseInt(this.applicationProperties.getProperty(DIGITAL_TWIN_PORT));
    }

    public String getAgvManagerServer() {
        return this.applicationProperties.getProperty(AGV_MANAGER_SERVER);
    }

    public int getAgvManagerPort() {
        return Integer.parseInt(this.applicationProperties.getProperty(AGV_MANAGER_PORT));
    }

    public int getHttpServerPort() {
        return Integer.parseInt(this.applicationProperties.getProperty(HTTP_SERVER_PORT));
    }

    public String getAutoAGVTaskAssignerFlag() {
        return this.applicationProperties.getProperty(AUTO_TASK_ASSIGNER);
    }

    public String getOrderServer() {
        return this.applicationProperties.getProperty(ORDER_SERVER);
    }

    public int getOrderServerPort() {
        return Integer.parseInt(this.applicationProperties.getProperty(ORDER_SERVER_PORT));
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
    public Map getExtendedPersistenceProperties() {
        final Map ret = new HashMap();
        ret.put(SCHEMA_GENERATION_KEY,
                this.applicationProperties.getProperty(SCHEMA_GENERATION_KEY));
        return ret;
    }

    public String getProperty(final String prop) {
        return this.applicationProperties.getProperty(prop);
    }
}
