import { ComponentFixture, TestBed } from '@angular/core/testing';

import { LlegadaPaqueteComponent } from './llegada-paquete.component';

describe('LlegadaPaqueteComponent', () => {
  let component: LlegadaPaqueteComponent;
  let fixture: ComponentFixture<LlegadaPaqueteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [LlegadaPaqueteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(LlegadaPaqueteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
