import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Bucket3Component } from './bucket3.component';

describe('Bucket3Component', () => {
  let component: Bucket3Component;
  let fixture: ComponentFixture<Bucket3Component>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Bucket3Component]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(Bucket3Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
