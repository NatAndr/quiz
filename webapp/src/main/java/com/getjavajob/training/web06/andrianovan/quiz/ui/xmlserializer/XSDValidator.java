package com.getjavajob.training.web06.andrianovan.quiz.ui.xmlserializer;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 18.01.2016.
 */
public class XSDValidator {

    public String validate(/*String XMLName, */String XSDName, File xmlFile) throws Exception {
        List<Source> schemas = new ArrayList<>();
        schemas.add(new StreamSource(ClassLoader.getSystemResourceAsStream(XSDName)));
//        Source source = new StreamSource(ClassLoader.getSystemResourceAsStream(XMLName));
        Source source = new StreamSource(new FileInputStream(xmlFile));
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Source[] schemaSources = new Source[schemas.size()];
        schemas.toArray(schemaSources);
        Schema schema = schemaFactory.newSchema(schemaSources);
        Validator validator = schema.newValidator();
        String result;
        try {
            validator.validate(source);
            result = "XML document is valid";
        } catch (SAXException e) {
            throw new SAXException("XML document is NOT valid. Reason " + e.getLocalizedMessage());
        }
        return result;
    }
}
