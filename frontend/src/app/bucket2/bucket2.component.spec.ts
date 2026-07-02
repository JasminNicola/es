import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bucket2Component } from './bucket2.component';

describe('Bucket2Component', () => {
  let component: Bucket2Component;
  let fixture: ComponentFixture<Bucket2Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Bucket2Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Bucket2Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
