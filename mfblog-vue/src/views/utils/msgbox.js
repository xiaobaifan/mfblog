//消息弹窗
import { createApp } from 'vue';
import MsesageBox from '../components/MessageBox.vue';
export default function showMsg(msg) {
    const app = createApp(MsesageBox, { msg });
    const div = document.createElement('div');
    app.mount(div);
    document.body.appendChild(div);

    return () => {
        app.unmount(div);
        document.body.removeChild(div);
    }
}