import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ClasificarEstadoComponent } from './clasificar-estado.component';

describe('ClasificarEstadoComponent', () => {
  let component: ClasificarEstadoComponent;
  let fixture: ComponentFixture<ClasificarEstadoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ClasificarEstadoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ClasificarEstadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
