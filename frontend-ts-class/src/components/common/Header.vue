<template>
    <div class='header'>
        <!-- Collapse-btn -->
        <div class='collapse-btn' @click='collapseChage'>
            <i v-if='!collapse' class='el-icon-s-fold'></i>
            <i v-else class='el-icon-s-unfold'></i>
        </div>
        <div class='logo'>POS - VUE</div>
        <div class='header-right'>
            <div class='header-user-con'>
                <!-- fullscreen -->
                <div class='btn-fullscreen' @click='handleFullScreen'>
                    <el-tooltip effect='dark' :content='fullscreen?`No Fullscreen`:`Fullscreen`' placement='bottom'>
                        <i class='el-icon-rank'></i>
                    </el-tooltip>
                </div>
                <!-- User icon -->
                <div class='user-avator'>
                    <img :src='userInfo.avatarBase64' />
                </div>
                <!-- Meun for user -->
                <el-dropdown class='user-name' trigger='click' @command='handleCommand'>
                    <span class='el-dropdown-link'>
                        {{userInfo.username}}
                        <i class='el-icon-caret-bottom'></i>
                    </span>
                    <el-dropdown-menu slot='dropdown'>
                        <el-dropdown-item divided command='userindex'>Self File</el-dropdown-item>
                        <el-dropdown-item divided command='loginout'>Logout</el-dropdown-item>
                    </el-dropdown-menu>
                </el-dropdown>
            </div>
        </div>
    </div>
</template>
<script lang='ts'>
import { Component, Vue } from 'vue-property-decorator'
import axios from '@/axios'
import bus from './bus'

@Component
export default class vHead extends Vue {

    collapse: boolean = false
    fullscreen: boolean = false
    name: string = 'admin'
    message: number = 2

    get userInfo() {
        return this.$store.state.userProfile
    }

    created() {
        this.getUserInfo()
    }

    getUserInfo() {
        axios.get('/sys/userInfo').then(res => {
            // this.userInfo = res.data.data
            this.$store.commit('setUserProfile', res.data.data)
        })
    }
        // Username dropdown menu selection event
    handleCommand(command) {
        if (command == 'loginout') {
            axios.post('/logout').then(res => {
                localStorage.clear()
                sessionStorage.clear()
                this.$store.commit('resetState')
                this.$store.commit('resetHasRoutes')

                    this.$router.push('/login')
                })
        }
        if (command == 'userindex') {
            this.$router.push('/userindex')
        }
    }
        // sidebar collapse
    collapseChage() {
        this.collapse = !this.collapse;
        bus.$emit('collapse', this.collapse);
    }
        // Fullscreen Event
    handleFullScreen() {
        let element = document.documentElement;
        if (this.fullscreen) {
            if (document.exitFullscreen) {
                document.exitFullscreen();
            } else if (document['webkitCancelFullScreen']) {
                document['webkitCancelFullScreen']();
            } else if (document['mozCancelFullScreen']) {
                document['mozCancelFullScreen']();
            } else if (document['msExitFullscreen']) {
                    document['msExitFullscreen']();
                }
            } else {
                if (element.requestFullscreen) {
                    element.requestFullscreen();
                } else if (element['webkitRequestFullScreen']) {
                    element['webkitRequestFullScreen']();
                } else if (element['mozRequestFullScreen']) {
                    element['mozRequestFullScreen']();
                } else if (element['msRequestFullscreen']) {
                // IE11
                element['msRequestFullscreen']();
            }
        }
        this.fullscreen = !this.fullscreen;
    }

    mounted() {
        if (document.body.clientWidth < 1500) {
            this.collapseChage()
        }
    }
}
</script>
<style scoped>
.header {
    position: relative;
    box-sizing: border-box;
    width: 100%;
    height: 70px;
    font-size: 22px;
    color: #fff;
}
.collapse-btn {
    float: left;
    padding: 0 21px;
    cursor: pointer;
    line-height: 70px;
}
.header .logo {
    float: left;
    width: 250px;
    line-height: 70px;
}
.header-right {
    float: right;
    padding-right: 50px;
}
.header-user-con {
    display: flex;
    height: 70px;
    align-items: center;
}
.btn-fullscreen {
    transform: rotate(45deg);
    margin-right: 5px;
    font-size: 24px;
}
.btn-bell,
.btn-fullscreen {
    position: relative;
    width: 30px;
    height: 30px;
    text-align: center;
    border-radius: 15px;
    cursor: pointer;
}
.btn-bell-badge {
    position: absolute;
    right: 0;
    top: -2px;
    width: 8px;
    height: 8px;
    border-radius: 4px;
    background: #f56c6c;
    color: #fff;
}
.btn-bell .el-icon-bell {
    color: #fff;
}
.user-name {
    margin-left: 10px;
}
.user-avator {
    margin-left: 20px;
}
.user-avator img {
    display: block;
    width: 40px;
    height: 40px;
    border-radius: 50%;
}
.el-dropdown-link {
    color: #fff;
    cursor: pointer;
}
.el-dropdown-menu__item {
    text-align: center;
}
</style>
