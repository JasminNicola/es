import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bucket4Component } from './bucket4.component';

describe('Bucket4Component', () => {
  let component: Bucket4Component;
  let fixture: ComponentFixture<Bucket4Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Bucket4Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Bucket4Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
