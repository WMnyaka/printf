#include <stdarg.h>
#include "main.h"

/**
 * _printf - produces output according to a format
 * @format: character string
 * Return: number of characters printed excluding null byte
 */
int _printf(const char *format, ...)
{
    int printed = 0;
    int temp;
    int divisor;
    int i;
    va_list args;
    va_start(args, format);

    while (*format)
    {
        if (*format == '%')
        {
            format++;
            if (*format == '\0')
                break;

            switch (*format)
            {
                case 'c':
                    _putchar(va_arg(args, int));
                    printed++;
                    break;

                case 's':
                {
                    const char *str = va_arg(args, const char *);
                    while (*str)
                    {
                        _putchar(*str);
                        str++;
                        printed++;
                    }
                    break;
                }

                case 'd':
                case 'i':
                {
                    int num = va_arg(args, int);
                    int num_chars = 0;

                    if (num < 0)
                    {
                        _putchar('-');
                        printed++;
                        num = -num;
                    }

                    temp = num;
                    do
                    {
                        temp /= 10;
                        num_chars++;
                    } while (temp);

                    divisor = 1;
                    for (i = 1; i < num_chars; i++)
                        divisor *= 10;

                    while (divisor)
                    {
                        int digit = num / divisor;
                        _putchar(digit + '0');
                        printed++;
                        num %= divisor;
                        divisor /= 10;
                    }

                    _putchar(num + '0');
                    printed++;
                    break;
                }

                case '%':
                    _putchar('%');
                    printed++;
                    break;

                default:
                    break;
            }
        }
        else
        {
            _putchar(*format);
            printed++;
        }

        format++;
    }

    va_end(args);
    return (printed);
}
