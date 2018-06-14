package com.overall.developer.overrendicion.data.model.entity;

public class TipoGastoEntity
{
        private String rtgId;
        private String rtgDes;

        public TipoGastoEntity() {
        }

        public TipoGastoEntity(String rtgId, String rtgDes) {
            this.rtgId = rtgId;
            this.rtgDes = rtgDes;
        }

        public String getRtgId() {
            return rtgId;
        }

        public void setRtgId(String rtgId) {
            this.rtgId = rtgId;
        }

        public String getRtgDes() {
            return rtgDes;
        }

        public void setRtgDes(String rtgDes) {
            this.rtgDes = rtgDes;
        }

    @Override
    public String toString() {
        return rtgDes;
    }

}
