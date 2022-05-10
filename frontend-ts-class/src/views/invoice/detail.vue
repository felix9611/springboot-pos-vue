<template>
  <div class="container">
    <div class="handle-box">
      <div class="handle-box">
        <el-button icon="el-icon-back" circle @click="back"></el-button>
      </div>
    </div>
    <el-form :model="editForm" :disabled="true">
      <el-row :span="24">
        <el-col :span="6">
          <el-form-item label="Invoice Code"  label-width="160px">
            <el-input v-model="detailForm.number" autocomplete="off" readonly></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="Member Name"  label-width="130px">
            <el-input v-model="detailForm.memberName" autocomplete="off"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="Member Phone"  label-width="130px">
            <el-input v-model="detailForm.memberPhone" autocomplete="off"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="6">
          <el-form-item label="Member Class"  label-width="130px">
            <el-input v-model="detailForm.mcName" autocomplete="off"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :span="24">
        <el-col :span="12">
          <el-form-item label="Location/Shop Name"  label-width="160px">
            <el-input v-model="detailForm.placeName" autocomplete="off"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Total Amount"  label-width="130px">
            <el-input v-model="detailForm.totalAmount" autocomplete="off"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :span="24">
        <el-col :span="12">
          <el-form-item label="Created Date"  label-width="160px">
            <el-input v-model="detailForm.createdAt" autocomplete="off"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="Void Date"  label-width="130px">
            <el-input v-model="detailForm.voidAt" autocomplete="off"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <br>
    <h2>Payment</h2>
    <br>
    <el-table
      ref="multipleTable"
      :data="itemPay"
      tooltip-effect="dark">
      <el-table-column 
          label="Amount" 
          prop="amount">
        </el-table-column>
       <el-table-column 
          label="Method" 
          prop="method">
        </el-table-column>
    </el-table>
    <br>
    <h2>Detail List</h2>
    <br>
    <el-table
      label=""
      ref="multipleTable"
      :data="items"
      tooltip-effect="dark">
      <el-table-column 
          label="Product Code" 
          prop="productCode">
        </el-table-column>
        <el-table-column 
          label="Product Name" 
          prop="productName">
        </el-table-column>
       <el-table-column 
          label="Qty" 
          prop="qty">
        </el-table-column>
        <el-table-column 
          label="Price" 
          prop="price">
        </el-table-column>
        <el-table-column 
          label="Discount" 
          prop="discount">
        </el-table-column>
        <el-table-column 
          label="Discount Type" 
          prop="discountType">
        </el-table-column>
    </el-table>
  </div>
</template>
<script lang="ts">
import axios from '@/axios'
import moment from 'moment'
import { Component, Vue, Watch } from 'vue-property-decorator'

@Component
export default class InoviceDetail extends Vue {
  get invoiceId() {
    return this.$route.params.id
  }
  detailForm: any = {}
  itemPay: any = []
  items: any = []

  created() {
    this.getDetail()
    this.getPayment()
    this.getItem()
  }

  getDetail() {
    axios.get(`/invoice/detail/${this.invoiceId}`).then(
      (res: any) => {
        this.detailForm = res.data.data
        this.detailForm.createdAt = moment(new Date(this.detailForm.createdAt)).format('DD-MM-YYYY HH:MM')
      }
    )
  }

  getPayment() {
    axios.get(`/payment/list/${this.invoiceId}`).then(
      (res: any) => {
        this.itemPay = res.data.data
      }
    )
  }

  getItem() {
    axios.get(`/invoice/item/${this.invoiceId}`).then(
      (res: any) => {
        this.items = res.data.data
      }
    )
  }


  back() {
    this.$router.push({ path: '/invoice' })
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
