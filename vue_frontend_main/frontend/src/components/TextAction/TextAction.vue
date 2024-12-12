<template>
  <div class="typing-container">
    <el-text style="font-size: 20px;  overflow-wrap: break-word;" ref="typingText" :class="{ active: isTyping }">{{ displayedText }}</el-text>
    <span class="cursor" :style="{ opacity: cursorBlink ? 1 : 0 }">|</span>
  </div>
</template>

<script setup>
import { ref, onMounted, watch, computed } from 'vue';

const props = defineProps({
  textList: {
    type: Array,
    default: () => [],
  },
});

const typingSpeed = 500; // 每个字符出现的速度（毫秒）
const cursorBlinkSpeed = 500; // 光标闪烁的速度（毫秒）

const isTyping = ref(false);
let intervalId;
const displayedText = ref('');
let currentTextIndex = -1;

const cursorBlink = computed(() => {
  return setInterval(() => {
    cursorOpacity.value = !cursorOpacity.value;
  }, cursorBlinkSpeed / 2);
}, []);

const cursorOpacity = ref(1);

onMounted(() => {
  if (props.textList.length>0){
    startTyping();
  }

});

const startTyping = () => {
  if (props.textList.length > currentTextIndex + 1) {
    isTyping.value = true;
    currentTextIndex++;
    const currentText = props.textList[currentTextIndex];
    // let charIndex = 0;
    intervalId = setInterval(() => {
      displayedText.value+=currentText;
      if(displayedText.value.length>=currentText.length){
        clearInterval(intervalId);
        setTimeout(() => {
          isTyping.value = false;
          displayedText.value = '';
          startTyping();
        }, 1000);
      }
      // if (charIndex === currentText.length) {
      //   clearInterval(intervalId);
      //   setTimeout(() => {
      //     isTyping.value = false;
      //     // if(props.textList.length > currentTextIndex + 1)
      //     //   startTyping()
      //       // 添加适当的延迟后开始下一段文字
      //   }, 1000); // 可根据需求调整延时时间
      // }
    }, typingSpeed);
  }
};

watch(
    () => props.textList,
    (newList) => {
      currentTextIndex = -1;
      displayedText.value = '';
      startTyping();
    },
    { immediate: true }
);
</script>

<style scoped>
.typing-container {
  position: relative;
  overflow: hidden;
  width: fit-content;
}

.cursor {
  display: inline-block;
  animation: blinker steps(1, start) 500ms infinite;
}

@keyframes blinker {
  50% {
    opacity: 0;
  }
}
</style>
