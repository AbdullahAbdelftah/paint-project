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
      <div>
        <div v-for="(color, index) in colorPalette" :key="index" @click="setColor(color)">
          <div :style="{ backgroundColor: color }" class="color-square"></div>
        </div>
        <div>
          Selected Color: {{ selectedColor }}
        </div>
      </div>
      
    </div>
    <div class="stage">
      <v-stage :config="configKonva">
        <v-layer>
          <v-circle v-for="(circle, index) in shapes.circles" :key="circle.id" draggable="true" @dragstart="newInd(index)" :config="circle" @dragend="drageNew" @dblclick="showResizeForm(index,circle)" @click="changeColor(circle)"></v-circle>
          <v-rect v-for="(rect, index) in shapes.rectangles" :key="rect.id" draggable="true" @dragstart="newInd(index)" :config="rect" @dragend="drageNewR" @dblclick="showResizeForm(index,rect)" @click="changeColor(rect)"></v-rect>
          <v-line v-for="(line, index) in shapes.lines" :key="line.id" draggable="true" @dragstart="newInd(index)" :config="line" @dragend="drageNewL" @dblclick="showResizeForm(index,line)" @click="changeColor(line)"></v-line>
          <v-rect v-for="(sq, index) in shapes.squares" :key="sq.id" draggable="true" @dragstart="newInd(index)" :config="sq" @dragend="drageNewS" @dblclick="showResizeForm(index,sq)" @click="changeColor(sq)"></v-rect>
          <v-ellipse v-for="(ellipse, index) in shapes.ellipses" :key="ellipse.id" draggable="true" @dragstart="newInd(index)" :config="ellipse" @dragend="drageNewE" @dblclick="showResizeForm(index,ellipse)" @click="changeColor(ellipse)"></v-ellipse>
        </v-layer >
      </v-stage>
    </div>
  </div>
  <div v-if="showForm">
    <label v-if="selectedShapeType==='rectangle'">
      Width:
      <input type="number" v-model="resizeForm.width" />
    </label>
    <label v-if="selectedShapeType==='rectangle'">
      Height:
      <input type="number" v-model="resizeForm.height" />
    </label>
    <label v-if="selectedShapeType==='square'">
      side length:
      <input type="number" v-model="resizeForm.width" />
    </label>
    <label v-if="selectedShapeType==='circle'">
      radius:
      <input type="number" v-model="resizeForm.width" />
    </label>
    <label v-if="selectedShapeType==='ellipse'">
      radiusX:
      <input type="number" v-model="resizeForm.width" />
    </label>
    <label v-if="selectedShapeType==='ellipse'">
      radiusY:
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
      colorPalette: [
        "#FF0000", "#FF7F00", "#FFFF00", "#7FFF00",
        "#00FF00", "#00FFFF", "#007FFF",
        "#0000FF", "#7F00FF", "#FF00FF",
        "#FFFFFF", "#C0C0C0", "#808080", "#000000"
      ],
      selectedColor: null,
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
      selectedShapeType: null,
      resizeForm: {
        width: 0,
        height: 0,
      },
      shapeIdCounter: 1,
      configKonva: {
        width: 1400,
        height: 600
      },
    };
  },
  methods: {
    showResizeForm(index,shape) {
      this.selectedShapeIndex = index;
      this.showForm = true;
      this.selectedShapeType=shape.type;
    },
    changeColor(shape){
      if(this.selectedColor){
      if(shape.type==="rectangle"){
        this.shapes.rectangles[shape.index].fill=this.selectedColor.toLowerCase();
      }
      if(shape.type==="circle"){
        this.shapes.circles[shape.index].fill=this.selectedColor.toLowerCase();
      }
      if(shape.type==="ellipse"){
        this.shapes.ellipses[shape.index].fill=this.selectedColor.toLowerCase();
      }
      if(shape.type==="line"){
        this.shapes.lines[shape.index].fill=this.selectedColor.toLowerCase();
        
    }
    if(shape.type==="square"){
        this.shapes.squares[shape.index].fill=this.selectedColor.toLowerCase();
      }
      }
      this.selectedColor=null;
    },
    applyResize() {
      if (this.selectedShapeIndex !== null) {
        if(this.selectedShapeType==="rectangle"){
        if (this.resizeForm.width) {
          this.shapes.rectangles[this.selectedShapeIndex].width = this.resizeForm.width;
        }
        if (this.resizeForm.height) {
          this.shapes.rectangles[this.selectedShapeIndex].height = this.resizeForm.height;
        }
        this.showForm = false;
      }
      if(this.selectedShapeType==="circle"){
        if (this.resizeForm.width) {
          this.shapes.circles[this.selectedShapeIndex].radius = this.resizeForm.width;
        }
        this.showForm = false;
      }
      if(this.selectedShapeType==="square"){
        if (this.resizeForm.width) {
          this.shapes.squares[this.selectedShapeIndex].width = this.resizeForm.width;
          this.shapes.squares[this.selectedShapeIndex].height = this.resizeForm.width;
        }
        this.showForm = false;
      }
      if(this.selectedShapeType==="ellipse"){
        if (this.resizeForm.width) {
          this.shapes.ellipses[this.selectedShapeIndex].radiusX = this.resizeForm.width;
          
        }
        if(this.resizeForm.height){
          this.shapes.ellipses[this.selectedShapeIndex].radiusY = this.resizeForm.height;
        }
        this.showForm = false;
      }
      this.resizeForm.width=0;
      this.resizeForm.height=0;
    }
    },
    newInd(index) {
    this.draggedShapeIndex = index;
  },
  addEllipse() {
    this.shapes.ellipses.push({
      index: this.shapes.ellipses.length,
      id: this.shapeIdCounter++,
      type:"ellipse",
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
    }).catch(err=>{
      console.log(err);
    })
    fetch('http://localhost:8080/squares', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.shapes.squares)
    }).catch(err=>{
      console.log(err);
    })
    fetch('http://localhost:8080/rectangles', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(this.shapes.rectangles)
    }).catch(err=>{
      console.log(err);
    })
    fetch('http://localhost:8080/writeJson', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    })
  },
  async write(){
    await fetch('http://localhost:8080/writeJson', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
    })
  },
  setColor(color) {
      this.selectedColor =this.hexToColorNameWithComparison(color);
    },
     hexToColorNameWithComparison(hexCode)  {
      let colorMappings = {
        '#FF0000': 'Red',
        '#FF7F00': 'Orange',
        '#FFFF00': 'Yellow',
        '#7FFF00': 'Chartreuse',
        '#00FF00': 'Lime',
        '#00FF7F': 'Spring Green',
        '#00FFFF': 'Cyan',
        '#007FFF': 'Azure',
        '#0000FF': 'Blue',
        '#7F00FF': 'Violet',
        '#FF00FF': 'Magenta',
        '#FF007F': 'Rose',
        '#FFFFFF': 'White',
        '#C0C0C0': 'Silver',
        '#808080': 'Gray',
        '#000000': 'Black',
      };

      let colorName = colorMappings[hexCode.toUpperCase()];
      return colorName ? colorName : 'Unknown';
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
.color-square {
  width: 10px;
  height: 10px;
  border: 1px solid #000;
  cursor: pointer;
  margin: 5px;
}
</style>
