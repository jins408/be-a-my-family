<template>
  <v-container>
    <v-col cols="10" style="margin:0 auto;">
      <div class="d-flex mb-5">
        <i class="fas fa-dog" style="font-size:30px"></i>
        <h2 class="ml-3">입양후기</h2>
      </div>

      <v-row align="center">
        <v-col cols="12" sm="4"></v-col>
        <v-col cols="12" sm="3" class="mt-5">
          <v-select
            v-model="category"
            :items="items"
            label="카테고리"
            item-text="state"
            item-value="abbr"
            solo
            class="ml-5"
            style="margin-left:auto;"
          ></v-select>
        </v-col>

        <v-col cols="12" sm="5">
          <v-text-field
            v-model="searchText"
            @keypress.enter="search"
            append-icon="mdi-magnify"
            label="Search"
            single-line
            hide-details
            class="mb-5"
            style="width:400px; margin-left: auto;"
          ></v-text-field>
        </v-col>
      </v-row>

      <div class="d-flex">
        <!-- <v-btn @click="create" class="mt-5" style="margin-left:auto">후기작성</v-btn> -->
        <v-btn @click="create" style="margin-left:auto" outlined color="blue">
          <v-icon left>mdi-pencil</v-icon>후기작성
        </v-btn>
      </div>

      <v-simple-table 
        class="mt-5" 
        page.sync="page">

        <template v-slot:default>
          <thead>
            <tr>
              <th class="text-left">No.</th>
              <th class="text-left">Title</th>
              <th class="text-left">Writer</th>
              <th class="text-left">Data</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="(adopt, index) in adoptData" :key="index" @click="adoptdetail(adopt.postscriptno)">
              <th scope="row">{{ adopt.postscriptno }}</th>
              <td>{{ adopt.title }}</td>
              <td>{{ adopt.uid}}</td>
              <td>{{ nowdate(adopt.createdate)}}</td>
            </tr>
          </tbody>
        </template>
      </v-simple-table>

      <v-pagination v-model="page" :length="pageCount"  :total-visible="5" circle class="mt-5"></v-pagination>
    </v-col>
  </v-container>
</template>

<script>
import constants from "@/lib/constants";
import axios from "axios";

import { mapState, mapActions } from "vuex";

export default {
  name: "Adoptlist",
  computed:{

  },
  created() {
    this.adoptList();

  },
  data() {
    return {
      page: 1,
      pageCount: 15,
  
      items: [                
          { state: '글제목', abbr: 'title' },
          { state: '작성자', abbr: 'uid' }],
      category: {},
      searchText: '',

      adoptData: {
        postscriptno: "",
        uid: "",
        title: "",
        content: "",
        image: "",
        sido: "",
        gugun: "",
        kind: "",
        createdate: "",
      },
    };
  },
  methods: {
    adoptList(){
      axios
          .get(constants.SERVER_URL + "/postscript/List")
          .then((res) =>{
            this.adoptData = res.data.object
            console.log(this.adoptData)
          })
          .catch((err)=>{
            console.log(err)
          })
    },
    create() {
      if(!this.$cookies.isKey("auth-token")){
            alert('로그인해주세요')
        }else{
        this.$router.push({ name: constants.URL_TYPE.ADOPTIONPOST.ADOPTCREATE })}
    },
    adoptdetail(postscriptno) {
      this.$router.push({ name: constants.URL_TYPE.ADOPTIONPOST.ADOPTDETAIL, params:{ ID: postscriptno} })
    },
    nowdate(createdate){
        var nowdate = createdate+""
        return nowdate.substring(0,10)
    },
    search(){
        console.log(this.category)
        console.log(this.searchText)
        if(this.searchText === ""){
                this.adoptList()
        }else{
            axios.get(constants.SERVER_URL + `/postscript/Search?category=${this.category}&searchText=${this.searchText}`)
            .then((response) =>{
                this.adoptData = response.data.object
                this.searchText = ""
                console.log(response)

            })
            .catch(() =>{
                alert("올바른 값을 입력하세요")
            })
        }

    },
  },
};
</script>

<style scoped>
</style>