<template>
  <div class="all">
    <div class="bts">
      <button @click="addCircle()"><i class="fa-solid fa-circle"></i></button>
      <button @click="addEllipse()">ellipse</button>
      <button @click="addRectangle()"><i class="fa-solid fa-rectangle-list"></i></button>
      <button @click="addLine()"><i class="fa-solid fa-grip-lines"></i></button>
      <button @click="addSquare()"><i class="fa-solid fa-square"></i></button>
      <button @click="save()"><i class="fa-solid fa-floppy-disk"></i></button>
      <button @click="load()"><i class="fa-solid fa-floppy-disk"></i></button>
    </div>
    <div class="stage">
      <v-stage :config="configKonva">
        <v-layer>
          <v-circle v-for="(circle, index) in shapes.circles" :key="circle.id" draggable="true" @dragstart="newInd(index)" :config="circle" @dragend="drageNew"></v-circle>
          <v-rect v-for="(rect, index) in shapes.rectangles" :key="rect.id" draggable="true" @dragstart="newInd(index)" :config="rect" @dragend="drageNewR" @dblclick="showResizeForm(index)"></v-rect>
          <v-line v-for="(line, index) in shapes.lines" :key="line.id" draggable="true" @dragstart="newInd(index)" :config="line" @dragend="drageNewL"></v-line>
          <v-rect v-for="(sq, index) in shapes.squares" :key="sq.id" draggable="true" @dragstart="newInd(index)" :config="sq" @dragend="drageNewS"></v-rect>
          <v-ellipse v-for="(ellipse, index) in shapes.ellipses" :key="ellipse.id" draggable="true" @dragstart="newInd(index)" :config="ellipse" @dragend="drageNewE"></v-ellipse>
        </v-layer >
      </v-stage>
    </div>
  </div>
  <div v-if="showForm">
    <label>
      Width:
      <input type="number" v-model="resizeForm.width" />
    </label>
    <label>
      Height:
      <input type="number" v-model="resizeForm.height" />
    </label>
    <button @click="applyResize">Apply</button>
  </div>
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
        squares:[],
        ellipses:[],
        
      },
      showForm: false,
      selectedShapeIndex: null,
      resizeForm: {
        width: false,
        height: false,
      },
      shapeIdCounter: 1,
      configKonva: {
        width: 1400,
        height: 600
      },
    };
  },
  methods: {
    showResizeForm(index) {
      this.selectedShapeIndex = index;
      this.showForm = true;
      
    },
    applyResize() {
      if (this.selectedShapeIndex !== null) {
        if (this.resizeForm.width) {
          this.shapes.rectangles[this.selectedShapeIndex].width = this.resizeForm.width;
        }

        if (this.resizeForm.height) {
          this.shapes.rectangles[this.selectedShapeIndex].height = this.resizeForm.height;
        }
        this.showForm = false;
      }
    },
    newInd(index) {
    this.draggedShapeIndex = index;
  },
  addEllipse() {
  this.shapes.ellipses.push({
    index: this.shapes.ellipses.length,
    id: this.shapeIdCounter++,
    type:"Ellipse",
    x: 100,
    y: 100,
    radiusX: 70,
    radiusY: 50,
    fill: "blue",
    stroke: "black",
    strokeWidth: 4,
  });
},

    addRectangle() {

      this.shapes.rectangles.push({
        index: this.shapes.rectangles.length,
        id: this.shapeIdCounter++,
        type:"rectangle",
        x: 100,
        y: 100,
        width:100,
        height:60,
        fill: "red",
        stroke: "black",
        strokeWidth: 4,
      });
      
    },
    addCircle() {
      this.shapes.circles.push({
        index: this.shapes.circles.length,
        id:this.initID++,
        type:"circle",
        x: 100,
        y: 100,
        radius: 70,
        fill: "red",
        stroke: "black",
        strokeWidth: 4,

      });
    },
    addLine() {
      this.shapes.lines.push({
        index: this.shapes.lines.length,
        type:"line",
        x: 100,
        y: 100,
        points: [300, 300, 400, 400],
        stroke: 'green',
        strokeWidth: 5,
      });
    },
    addSquare() {

      this.shapes.squares.push({
        index: this.shapes.squares.length,
        id: this.shapeIdCounter++,
        type:"square",
        x: 100,
        y: 100,
        width:100,
        height:100,
        fill: "pink",
        stroke: "black",
        strokeWidth: 4,
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
  },
  drageNewS(e) {
    if (this.draggedShapeIndex !== null) {
      this.shapes.squares[this.draggedShapeIndex].x = e.target.attrs.x;
      this.shapes.squares[this.draggedShapeIndex].y = e.target.attrs.y;
      console.log(JSON.stringify(this.shapes.squares));
    }
  },
  drageNewE(e) {
  if (this.draggedShapeIndex !== null) {
    this.shapes.ellipses[this.draggedShapeIndex].x = e.target.attrs.x;
    this.shapes.ellipses[this.draggedShapeIndex].y = e.target.attrs.y;
    console.log(this.shapes.ellipses);
  }
},

  save(){
    fetch('http://localhost:8080/circles', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.shapes.circles)
    }).then(res=>{
      return res.json();
    }).then(response=>{
      for(let i=0; i<response.length; i++){
        this.shapes.circles.push(response[i]);
      }
    })
    fetch('http://localhost:8080/squares', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.shapes.squares)
    }).then(res=>{
      return res.json();
    }).then(response=>{
      for(let i=0; i<response.length; i++){
        this.shapes.squares.push(response[i]);
      }
    })
    fetch('http://localhost:8080/rectangles', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.shapes.rectangles)
    }).then(res=>{
      return res.json();
    }).then(response=>{
      for(let i=0; i<response.length; i++){
        this.shapes.rectangles.push(response[i]);
      }
    })
    this.write();
    
  },
  async write(){
    await fetch('http://localhost:8080/writeJson', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    })
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
.all{
  display: flex;
}

.stage{
  border: #2c3e50 1px solid;
  
}
button{
  width: 50px;
  height: 50px;
}
.bts{
  width: 200px;
}
</style>
