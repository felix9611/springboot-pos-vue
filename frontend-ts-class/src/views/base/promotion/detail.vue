<template>
    <div class="w-full bg-white p-1 shadow-lg rounded-lg">
        <div class="handle-box">
            <div class="handle-box p-1">
                <el-button icon="el-icon-back" circle @click="back"></el-button>
                <el-button icon="el-icon-circle-plus" circle v-if="readonlyMode === true" @click="startEdit()"></el-button>
            </div>
        </div>
        <el-form :model="editForm" :disabled="readonlyMode" class="grid lg:grid-cols-4 gap-3 px-6">
            <el-form-item label="Promotion Code"  prop="promotionCode" label-width="120px">
                <el-input v-model="editForm.promotionCode" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="Promotion Name"  prop="promotionName" label-width="120px" class="lg:col-span-3">
                <el-input v-model="editForm.promotionName" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="Description"  prop="description" label-width="120px" class="lg:col-span-4">
                <el-input type="textarea" v-model="editForm.description" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="Period Start"  prop="description" label-width="120px">
                <el-date-picker
                    v-model="editForm.periodStart"
                    type="datetime"
                    placeholder="Period Start">
                  </el-date-picker>
            </el-form-item>
            <el-form-item label="Period End"  prop="description" label-width="120px">
                <el-date-picker
                    v-model="editForm.periodEnd"
                    type="datetime"
                    placeholder="Period End">
                  </el-date-picker>
            </el-form-item>
            <el-form-item label="Online" prop="online" label-width="120px">
                <el-checkbox v-model="editForm.online" />
            </el-form-item>
            <el-form-item label="In Store" prop="inStore" label-width="120px">
                <el-checkbox v-model="editForm.inStore" />
            </el-form-item>
            <el-form-item label="Member Only" prop="member" label-width="120px">
                <el-checkbox v-model="editForm.member" />
            </el-form-item>
            <el-form-item label="All to use one discount" prop="allOneDiscount" label-width="180px">
                <el-checkbox v-model="editForm.allOneDiscount" />
            </el-form-item>
            <el-form-item label="After Before Tax" prop="afterBeforeTax" label-width="180px">
                <el-checkbox v-model="editForm.afterBeforeTax" />
            </el-form-item>
            <el-form-item label="Coupon Requested" prop="couponRequest" label-width="180px">
                <el-checkbox v-model="editForm.couponRequest" />
            </el-form-item>
            <el-form-item label="Discount"  prop="discount" label-width="130px">
              <el-input-number v-model="editForm.discount" :step="1" class="w-full" ></el-input-number>
            </el-form-item>
            <el-form-item label="Discount Type"  prop="discount" label-width="130px">
                <el-select v-model="editForm.discountType" placeholder="Select" filterable :disabled="editForm.allOneDiscount">
                <el-option
                    v-for="discount in discountList"
                    :key="discount.type"
                    :label="discount.type"
                    :value="discount.type">
                </el-option>
                </el-select>
            </el-form-item>
            <el-form-item label="Coupon Main Code"  prop="couponMainCode" label-width="150px" :disabled="editForm.couponRequest === false || editForm.couponRequest === null">
                <el-input v-model="editForm.couponMainCode" autocomplete="off"></el-input>
            </el-form-item>
            <el-form-item label="Remark"  prop="Remark" label-width="120px" class="lg:col-span-4">
                <el-input type="textarea" v-model="editForm.remark" autocomplete="off"></el-input>
            </el-form-item>
        </el-form>
    </div>
</template>
<script lang="ts">
import axios from '@/axios'
import VueBase64FileUpload from 'vue-base64-file-upload'
import type { UploadFile } from 'element-plus/es/components/upload/src/upload.type'
import moment from 'moment'
import { Component, Vue, Watch } from 'vue-property-decorator'
import QrcodeVue from 'qrcode.vue'
import { uploadImgToBase64 } from '@/utils/uploadImgToBase64'

@Component
export default class PromotionDetail extends Vue {

    editForm: any = {}
    editFormRules: any = []
    readonlyMode: boolean = false
    hideSaveBtn: boolean = false

    discountList = [
        { type: '%' },
        { type: '$' },
    ]

    startEdit() {
        this.readonlyMode = false
    }

    back() {
        this.$router.push({ path: '/promotion' })
    }
}
</script>
<style scoped>

    .handle-box {
        margin-bottom: 20px;
    }

    .el-container {
      width: 100%;
      height: 100%;
      background-color: white;
    }
</style>