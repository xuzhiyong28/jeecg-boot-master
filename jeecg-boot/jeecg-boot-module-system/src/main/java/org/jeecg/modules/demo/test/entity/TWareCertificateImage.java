package org.jeecg.modules.demo.test.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author xuzhiyong
 * @createDate 2020-04-18-14:47
 */
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_WARE_CERTIFICATE_IMAGE")
public class TWareCertificateImage {
    @TableField(value = "COMPID")
    private Integer COMPID;

    @TableId(value = "SCANNO")
    private Integer SCANNO;

    @TableField(value = "WAREID")
    private Integer WAREID;
    @TableField(value = "FILETYPE")
    private Integer FILETYPE;
    @TableField(value = "BEGINDATE")
    private Date BEGINDATE;
    @TableField(value = "ENDDATE")
    private Date ENDDATE;
    @TableField(value = "IMG")
    private byte[] IMG;
    @TableField(value = "CREATEUSER")
    private Integer CREATEUSER;
    @TableField(value = "CREATETIME")
    private Date CREATETIME;
    @TableField(value = "LASTMODIFY")
    private Integer LASTMODIFY;
    @TableField(value = "LASTTIME")
    private Date LASTTIME;
    @TableField(value = "NOTES")
    private String NOTES;
    @TableField(value = "STAMP")
    private Integer STAMP;
    @TableField(value = "CERTIFICATENO")
    private String CERTIFICATENO;
    @TableField(value = "OPERATE_FLAG")
    private Integer OPERATE_FLAG;
    @TableField(value = "SCOPE")
    private String SCOPE;
    @TableField(value = "MAKENO")
    private String MAKENO;
    @TableField(value = "VENDORNO")
    private Integer VENDORNO;
    @TableField(value = "FILE_FORMAT")
    private String FILE_FORMAT;
    @TableField(value = "INTENTROWNO")
    private Integer INTENTROWNO;
    @TableField(value = "ACCEPTNO")
    private String ACCEPTNO;
    @TableField(value = "INTENTNO")
    private String INTENTNO;
    @TableField(value = "INVALIDATE_CHAR")
    private String INVALIDATE_CHAR;
    @TableField(value = "IBMNAME")
    private String IBMNAME;

}
