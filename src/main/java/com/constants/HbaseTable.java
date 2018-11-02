package com.constants;

/**
 * @Author: huangyunquan
 * @Description:Created on 2018/10/28 20:39.
 * 管理 hbase 表 枚举
 */
public enum HbaseTable {
    SCENE("sence_2i2c","2i2c场景表"),
        PROCESS_OPENUSER("process_2i2c_openuser","2i2c开户流程表"),
            TACHE_USER_TRADES("tache_2i2c_newusertrades","2i2c开户提交环节表"),
            TACHE_NEW_USER("tache_2i2c_newusers","2i2c开户正式提交环节表"),
            TACHE_CARD_DATA("tache_2i2c_carddatas","2i2c写卡数据查询环节表"),
            TACHE_REMOTE_NOTIFY("tache_2i2c_remotecardstates","2i2c写卡结果通知环节表"),
            TACHE_DATA_SYNC("tache_2i2c_carddatasync","2i2c卡数据同步环节表"),
        PROCESS_ACTIVE("process_2i2c_active","2i2c激活流程表"),
        PROCESS_IOM("process_2i2c_iom","2i2c下省流程表"),
        PROCESS_BACK("process_2i2c_back","2i2c省份返回表"),
        PROCESS_FINISH("process_2i2c_finish","2i2c省份返回表");

    private String tableName;
    private String tableDesc;
    HbaseTable(String tableName, String tableDesc){
        this.tableName = tableName;
        this.tableDesc = tableDesc;
    }

    public String value(){
        return this.tableName;
    }

}
