import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { CheckboxModule } from 'primeng/checkbox';
import { DatePickerModule } from 'primeng/datepicker';
import { TaskService } from '../services/task.service';
import { Task } from '../../models/task';
import { TableModule } from 'primeng/table';

@Component({
  selector: 'app-homepage',
  standalone: true,
  imports: [CommonModule, FormsModule, HttpClientModule, ButtonModule, InputTextModule, CheckboxModule, DatePickerModule, TableModule],
  templateUrl: './homepage.html',
  styleUrl: './homepage.scss',
})
export class Homepage implements OnInit {
  //tasks: Task[] = [];
  newTask: string = '';
  date: Date | null = null;
newDate: any;
newNote: any;

  constructor(private taskService: TaskService) {}

  ngOnInit(): void {
    this.loadTasks();
  }

  tasks = [
    { id: 1, name: 'Task 1', age: 'Note 1', completed: false },
    { id: 2, name: 'Task 2', age: 'Note 2', completed: true },
    { id: 3, name: 'Task 3', age: 'Note 3', completed: false },
  ];


  loadTasks() {
    //this.taskService.getTasks().subscribe(data => this.tasks = data);
  }

  addTask() {
    if (!this.newTask.trim()) return;
  
    const task: Task = {
      title: this.newTask,
      completed: false
    };

    this.taskService.addTask(task).subscribe(() => {
      this.newTask = '';
      this.loadTasks();
    });
  }

  toggleComplete(task: Task) {
    task.completed = !task.completed;
    this.taskService.updateTask(task).subscribe();
  }

  deleteTask(id: number) {
    this.taskService.deleteTask(id).subscribe(() => this.loadTasks());
  }
}
