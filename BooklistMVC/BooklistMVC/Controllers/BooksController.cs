using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using BooklistMVC.Models;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace BooklistMVC.Controllers
{
    public class BooksController : Controller
    {
        private readonly ApplicationDbContext _db;

        [BindProperty]
        public Book Book { get; set; }

        public BooksController(ApplicationDbContext db)
        {
            _db = db;
        }

        // GET: /<controller>/
        public IActionResult Index()
        {
            return View();
        }

        public IActionResult Upsert(int? id)
        {
            Book = new Book();
            if (id == null)
            {
                //create
                return View(Book);
            }
            //update
            Book = _db.Books.FirstOrDefault(u => u.Id == id);
            if(Book == null)
            {
                return NotFound();
            }
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public IActionResult Upsert()
        {
           
            if (ModelState.IsValid)
            {
                if (Book.Id == 0)
                {
                    _db.Books.Add(Book);
                }
                else
                {
                    _db.Books.Update(Book);
                }
                _db.SaveChanges();
                return RedirectToAction("Index");
            }
            
            return View(Book);
        }

        #region API Calls
        [HttpGet]
        // GET: /<controller>/
        public async Task<IActionResult> GetAll()
        {
            //return View();
            return Json(new { data = await _db.Books.ToListAsync() });
        }

        [HttpDelete]
        public async Task<IActionResult> Delete(int id)
        {
            var bookFromDb = await _db.Books.FirstOrDefaultAsync(u => u.Id == id);
            if (bookFromDb == null)
            {
                return Json(new { success = false, message = "Error whihle deleting" });
            }
            _db.Books.Remove(bookFromDb);
            await _db.SaveChangesAsync();
            return Json(new { success = true, message = "Delete successful" });
        }


        #endregion
    }
}
