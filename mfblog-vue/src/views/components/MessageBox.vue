<template>
    <div class="message-box">
        <h3>{{ msg }}</h3>
        <!-- <button @click="close">关闭</button> -->
    </div>
</template>

<script setup>
defineProps({
    msg: {
        type: String,
        required: true
    }
});

// const emit = defineEmits(['close']);

// const close = () => {
//     emit('close');
// };

</script>

<style scoped>
.message-box {
    padding: 20px;
    background-color: #f8f9fa;
    border: 1px solid #dee2e6;
    border-radius: 5px;
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
}

h3 {
    margin: 0;
}
</style>
