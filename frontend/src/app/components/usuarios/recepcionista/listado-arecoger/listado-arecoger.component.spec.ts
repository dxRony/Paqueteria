import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoARecogerComponent } from './listado-arecoger.component';

describe('ListadoARecogerComponent', () => {
  let component: ListadoARecogerComponent;
  let fixture: ComponentFixture<ListadoARecogerComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListadoARecogerComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ListadoARecogerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
