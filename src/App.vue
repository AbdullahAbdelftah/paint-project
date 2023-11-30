<template>
  <v-stage :config="configKonva">
    <v-layer>
      <v-circle v-for="(circle, index) in shapes.circles" :key="circle.id" draggable="true" @dragstart="newInd(index)" :config="configCircle" @dragend="drageNew"></v-circle>
      <v-rect v-for="(rect, index) in shapes.rectangles" :key="rect.id" draggable="true" @dragstart="newInd(index)" :config="configRect" @dragend="drageNewR"></v-rect>
      <v-line v-for="(line, index) in shapes.lines" :key="line.id" draggable="true" @dragstart="newInd(index)" :config="configLine" @dragend="drageNewL"></v-line>

    </v-layer >
  </v-stage>
  <button @click="addCircle()">add circle</button>
  <button @click="addRectangle()">add rect</button>
  <button @click="addLine()">add line</button>
</template>

<script>
// import { shapes } from 'konva/lib/Shape';



export default {
  name: 'App',
  components: {
    
  },
  data() {
    return {
      draggedShapeIndex: null,
      ind:null,
      initID:0,
      shapes: {
        rectangles:[],
        circles: [],
        lines: [],
      },
      shapeIdCounter: 1,
      configKonva: {
        width: 1512,
        height: 600
      },
      configCircle: {
        x: 100,
        y: 100,
        radius: 70,
        fill: "red",
        stroke: "black",
        strokeWidth: 4,
        
      },
      configRect: {
        x: 100,
        y: 100,
        width:50,
        height:30,
        fill: "red",
        stroke: "black",
        strokeWidth: 4,
        
      },
      configLine: {
        x: 100,
        y: 100,
        points: [300, 300, 400, 400],
        stroke: 'green',
        strokeWidth: 5,
      }
    };
  },
  methods: {
    newInd(index) {
    this.draggedShapeIndex = index;
  },
    addRectangle() {

      this.shapes.rectangles.push({
        index: this.shapes.rectangles.length,
        id: this.shapeIdCounter++,
        x: 50,
        y: 50,
        width: 100,
        height: 80,
        fill: 'red',
        DragEvent:true
      });
      
    },
    addCircle() {
      this.shapes.circles.push({
        index: this.shapes.circles.length,
        id:this.initID++,
        x: 100,
        y: 100,
        radius: 70,
        fill: 'red',

      });
    },
    addLine() {
      this.shapes.lines.push({
        index: this.shapes.lines.length,
        x: 100,
        y: 100,
        points: [300, 300, 400, 400],
        stroke: 'green',
        strokeWidth: 5,
      });
    },
    drageNew(e) {
    if (this.draggedShapeIndex !== null) {
      this.shapes.circles[this.draggedShapeIndex].x = e.target.attrs.x;
      this.shapes.circles[this.draggedShapeIndex].y = e.target.attrs.y;
      console.log(this.shapes.circles);
    }
  },
  drageNewR(e) {
    if (this.draggedShapeIndex !== null) {
      this.shapes.rectangles[this.draggedShapeIndex].x = e.target.attrs.x;
      this.shapes.rectangles[this.draggedShapeIndex].y = e.target.attrs.y;
      console.log(this.shapes.rectangles);
    }
  },
  drageNewL(e) {
    if (this.draggedShapeIndex !== null) {
      this.shapes.lines[this.draggedShapeIndex].x = e.target.attrs.x;
      this.shapes.lines[this.draggedShapeIndex].y = e.target.attrs.y;
      console.log(this.shapes.lines);
    }
  }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
