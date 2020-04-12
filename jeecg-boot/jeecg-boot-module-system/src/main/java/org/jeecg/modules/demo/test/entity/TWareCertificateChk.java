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
 * @createDate 2020-04-11-11:36
 */
@Data
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("T_WARE_CERTIFICATE_CHK")
public class TWareCertificateChk {
    @TableField(value = "COMPID")
    private Integer COMPID;

    @TableId(value = "SCANNO")
    private Integer SCANNO;

    @TableField(value = "WAREID")
    private Integer WAREID;

    @TableField(value = "FILETYPE")
    private String FILETYPE;

    @TableField(value = "BEGINDATE")
    private Date BEGINDATE;

    @TableField(value = "ENDDATE")
    private Date ENDDATE;

    @TableField(value = "IMG")
    private byte[] IMG;

    @TableField(value = "CREATEUSER")
    private Integer CREATEUSER;

    @TableField(value = "LASTMODIFY")
    private Integer LASTMODIFY;

    @TableField(value = "LASTTIME")
    private Date LASTTIME;

    @TableField(value = "NOTES")
    private String NOTES;

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
    private String FILEFORMAT;

    @TableField(value = "WARECHKNO")
    private String WARECHKNO;

    @TableField(value = "FTPIP")
    private String FTPIP;

    @TableField(value = "FTPURL")
    private String FTPURL;











}
