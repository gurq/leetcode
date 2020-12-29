package interview.ant;

import interview.ant.GlobalVariable;

/**
 * bean
 *
 * @author gurq
 * @date 2020/11/30 10:05 下午
 */
public class Data {

    private String id;

    private String groupId;

    private float quota;

    @Override
    public String toString() {
        return groupId + GlobalVariable.SPLIT + id + GlobalVariable.SPLIT + quota;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public float getQuota() {
        return quota;
    }

    public void setQuota(float quota) {
        this.quota = quota;
    }
}
