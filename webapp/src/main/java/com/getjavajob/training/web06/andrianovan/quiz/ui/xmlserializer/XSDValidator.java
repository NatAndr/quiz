package com.getjavajob.training.web06.andrianovan.quiz.ui.xmlserializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 18.01.2016.
 */
public class XSDValidator {
    private static final Logger logger = LoggerFactory.getLogger(XSDValidator.class);
    private static final Logger errorLogger = LoggerFactory.getLogger("ErrorLogger");

    public String validate(String XSDName, File xmlFile) throws Exception {
        logger.debug("Going to validate XML document: {}", xmlFile.getName());
        List<Source> schemas = new ArrayList<>();
        schemas.add(new StreamSource(ClassLoader.getSystemResourceAsStream(XSDName)));
        Source source = new StreamSource(ClassLoader.getSystemResourceAsStream(xmlFile.getName()));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source[] schemaSources = new Source[schemas.size()];
        schemas.toArray(schemaSources);
        Schema schema = schemaFactory.newSchema(schemaSources);
        Validator validator = schema.newValidator();
        try {
            validator.validate(source);
            logger.debug("XML document is valid");
        } catch (SAXException e) {
            errorLogger.error("XML document {} is NOT valid. Reason: {}", xmlFile.getName(), e.getLocalizedMessage());
        }
        logger.debug("End of validate XML document");
        return "XML document is valid";
    }
}
