package web04;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

import java.util.List;

public class Dom4jDemo {

    @Test
    public void testFn(){

        try {
            //1、创建了一个SAX解析对象，可以操作Document文档流
            SAXReader sr = new SAXReader();
            //2、获取文档流对象
            Document document = sr.read("src/xml_demo.xml");
            //3、根据文档流对象查找跟节点元素
            Element rootElement = document.getRootElement();
            System.out.println("rootElement = " + rootElement.getName());
            //4、使用跟元素获取其它子元素
            List<Element> elements = rootElement.elements();
            for(Element e : elements){
                if(e.getName().equals("person")){
                    if(e.attributeValue("id").equals("p2")){
                        List<Element> pElements = e.elements();
                        for(Element pe : pElements){
                            System.out.println("name = " + pe.getName() + "val = "
                                    + pe.getText());
                        }
                    }
                }

            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
