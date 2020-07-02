package cn.cwnu.modules.sys.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class organizationTree implements Serializable {
    private static final long serialVersionUID = 1L;

    private  Long id;
    private  Long  pid;
    private  String title;
    private List<organizationTree> children;

}
