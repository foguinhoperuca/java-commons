(require 'psvn)
(global-set-key [M-left] 'windmove-left)          ; move to left windnow
(global-set-key [M-right] 'windmove-right)        ; move to right window
(global-set-key [M-up] 'windmove-up)              ; move to upper window
(global-set-key [M-down] 'windmove-down)          ; move to downer window
(custom-set-variables
 ;; custom-set-variables was added by Custom.
 ;; If you edit it by hand, you could mess it up, so be careful.
 ;; Your init file should contain only one such instance.
 ;; If there is more than one, they won't work right.
 '(column-number-mode t))
(custom-set-faces
 ;; custom-set-faces was added by Custom.
 ;; If you edit it by hand, you could mess it up, so be careful.
 ;; Your init file should contain only one such instance.
 ;; If there is more than one, they won't work right.
 )

(setq user-full-name
      "Jefferson Campos")
(setq user-mail-address
      "foguinho.peruca@gmail.com")

;; license lib
(load-library "~/.emacs.d/xlicense.el")

(define-abbrev-table 'global-abbrev-table
  '(
    ("$$license" "" license-skeleton)
    ))

(add-hook 'c-mode-hook (function (lambda nil (abbrev-mode 1))))
(add-hook 'c++-mode-hook (function (lambda nil (abbrev-mode 1))))

(add-hook 'java-mode-hook
	  (lambda ()
	    "Treat Java 1.5 @-style annotations as comments."
	    (setq c-comment-start-regexp "(@|/(/|[*][*]?))")
	    (modify-syntax-entry ?@ "< b" java-mode-syntax-table)))