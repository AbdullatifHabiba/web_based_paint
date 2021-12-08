import { Directive, ElementRef, HostListener, Output, Renderer2 } from '@angular/core';

@Directive({
  selector: '[appcanvas]'
})
export class canvasDirective {


   x:Number = 0;
   y:Number = 0;

  constructor(private el:ElementRef,renderer:Renderer2) {
//renderer.setStyle(el.nativeElement,'background','red');

  }
   /*@HostListener('mousedown')onmousedown(el:ElementRef,event:any){
    const rect = el.nativeElement.getBoundingClientRect()
    this. x = event.clientX-rect.left;
    this. y =event.clientY-rect.top;
   }*/



}
