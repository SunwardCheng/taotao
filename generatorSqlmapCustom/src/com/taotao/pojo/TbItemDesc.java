package com.taotao.pojo;

import java.util.Date;

public class TbItemDesc {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_desc.item_id
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    private Long itemId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_desc.created
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    private Date created;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_desc.updated
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    private Date updated;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_item_desc.item_desc
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    private String itemDesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_desc.item_id
     *
     * @return the value of tb_item_desc.item_id
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    public Long getItemId() {
        return itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_desc.item_id
     *
     * @param itemId the value for tb_item_desc.item_id
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_desc.created
     *
     * @return the value of tb_item_desc.created
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    public Date getCreated() {
        return created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_desc.created
     *
     * @param created the value for tb_item_desc.created
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_desc.updated
     *
     * @return the value of tb_item_desc.updated
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_desc.updated
     *
     * @param updated the value for tb_item_desc.updated
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_item_desc.item_desc
     *
     * @return the value of tb_item_desc.item_desc
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    public String getItemDesc() {
        return itemDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_item_desc.item_desc
     *
     * @param itemDesc the value for tb_item_desc.item_desc
     *
     * @mbggenerated Fri Apr 13 11:38:46 CST 2018
     */
    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc == null ? null : itemDesc.trim();
    }
}