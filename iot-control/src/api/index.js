import axios from 'axios'
const baseUrl = 'http://localhost:8081/api/v1/';
export const apiAxios = axios.create({
    baseURL:`${baseUrl}`,
    headers: {
        post: {
            'Content-Type': 'application/json',
        }
    }
})

export default {
    /**Get sensor
     *
     * @returns {Promise<axios.AxiosResponse<any>>}
     */
    getDHT11(){
        return apiAxios({
            method:'get',
            url:'dhts',
        })
    },
    getFan(){
        return apiAxios({
            method:'get',
            url:'control-modes/fan',
        })
    },
    getLamp(){
        return apiAxios({
            method:'get',
            url:'control-modes/lamp',
        })
    },
    /**Post control
     *
     * @param data
     * @returns {Promise<axios.AxiosResponse<any>>}
     */

    lampControl(data){
        return apiAxios({
            method:'post',
            url:'control-modes/lamp',
            data:data
        })
    },
    fanControl(data){
        return apiAxios({
            method:'post',
            url:'control-modes/fan',
            data:data
        })
    },

}