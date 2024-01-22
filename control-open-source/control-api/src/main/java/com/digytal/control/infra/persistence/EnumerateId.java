package com.digytal.control.infra.persistence;

import com.fasterxml.jackson.annotation.JsonFormat;

//@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public interface EnumerateId {
    String getId();
    String getDescricao();
    String getUpper();
}
