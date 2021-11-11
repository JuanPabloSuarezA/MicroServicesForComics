import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListarComicComponent } from './listar-comic.component';

describe('ListarComicComponent', () => {
  let component: ListarComicComponent;
  let fixture: ComponentFixture<ListarComicComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListarComicComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListarComicComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
