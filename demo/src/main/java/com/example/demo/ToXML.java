package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ToXML{
    @XmlElementWrapper(name = "shapes")
    @XmlElement
    private List<Shape> shapes;
}