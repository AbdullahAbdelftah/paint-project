<template>
  <div>
    <v-stage :width="width" :height="height">
      <v-layer>
        <!-- Render Shapes -->
        <v-rect
          v-for="rect in shapes.rectangles"
          :key="rect.id"
          :x="rect.x"
          :y="rect.y"
          :width="rect.width"
          :height="rect.height"
          :fill="rect.fill"
          :draggable="true"
          @click="handleShapeClick(rect)"
        />

        <v-circle
          v-for="circle in shapes.circles"
          :key="circle.id"
          :x="circle.x"
          :y="circle.y"
          :radius="circle.radius"
          :fill="circle.fill"
          @click="handleShapeClick(circle)"
        />

        <v-line
          v-for="line in shapes.lines"
          :key="line.id"
          :points="line.points"
          :stroke="line.stroke"
          :strokeWidth="line.strokeWidth"
          @click="handleShapeClick(line)"
          
        />
      </v-layer>
    </v-stage>

    
    <div>
      <button @click="addRectangle">Add Rectangle</button>
      <button @click="addCircle">Add Circle</button>
      <button @click="addLine">Add Line</button>
    </div>
  </div>
</template>

<script>


export default {
  name: 'App',
  components: {
    
  },
  data() {
    return {
      width: 800,
      height: 600,
      shapes: {
        rectangles: [],
        circles: [],
        lines: [],
      },
      shapeIdCounter: 1,
    };
  },
  methods: {
    handleShapeClick(shape) {
      console.log(shape.x);

    },
    addRectangle() {

      this.shapes.rectangles.push({
        id: this.shapeIdCounter++,
        x: 50,
        y: 50,
        width: 100,
        height: 80,
        fill: 'red',
        DragEvent:true
      });
      
    },
    printX(){
      
    },
    addCircle() {
      this.shapes.circles.push({
        id: this.shapeIdCounter++,
        x: 200,
        y: 150,
        radius: 50,
        fill: 'blue',
      });
    },
    addLine() {
      this.shapes.lines.push({
        id: this.shapeIdCounter++,
        points: [300, 300, 400, 400],
        stroke: 'green',
        strokeWidth: 5,
      });
    },
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
