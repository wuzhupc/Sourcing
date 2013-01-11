//
//  HtmlUtil.m
//  Fellow
//
//  Created by wuzhu on 12-11-21.
//
//

#import "HtmlUtil.h"
#import "StringUtil.h"
#import "DataInterfaceUtil.h"

#define CSTR_IMG_START @"<img "
#define CSTR_IMG_END @"/>"
#define CSTR_IMG_INSERT @" onload=\"fixImage(this,320,0)\" "
#define CSTR_IMG_REMOVE_START @"style=\""
#define CSTR_IMG_REMOVE_END @"\""
#define CSTR_IMG_SRC @" src="

@implementation HtmlUtil

+ (NSString *) getHtmlHead
{
    // 图像显示屏幕宽度的80%
    NSString *autoloadimgscript = @"<script type=\"text/javascript\" language=\"javascript\">  function fixImage(i,w,h){  var cw=document.body.clientWidth; if(cw*0.9<w) w=cw*0.9; var ow = i.width;   var oh = i.height;  var rw = w/ow; var rh = h/oh;  var r = Math.min(rw,rh);   if (w ==0 && h == 0){  r = 1;  }else if (w == 0){  r = rh<1?rh:1;  }else if (h == 0){  r = rw<1?rw:1;  }  if (ow!=0 && oh!=0){ i.width = ow * r;  i.height = oh * r;  }  else{ var __method = this, args = $A(arguments);  window.setTimeout(function() {    fixImage.apply(__method, args); }, 200); }  i.onload = function(){}  }  </script>";
    
    return [NSString stringWithFormat:@"<html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\">%@<style type=\"text/css\">body {background-color:#fcf2e9;}</style></head><body>", autoloadimgscript];
}

+ (NSString *) getHtmlEnd
{
    return @"<br/><br/></body></html>";
}

+ (NSString *)getHtmlErrorHit:(NSString *)kmsg hasreload:(BOOL)khas
{
    NSString *tmp = [NSString stringWithFormat:@"<br/>&nbsp;&nbsp;很抱歉，您访问的内容%@无法正常加载。",[StringUtil isEmpty:kmsg]?@"":[NSString stringWithFormat:@"因为%@的原因而",kmsg]];
    if(khas)
        return [NSString stringWithFormat:@"%@<a href=\"%@\">请偿试重新加载</a>",tmp,CSTR_RELOADLINK];
    return tmp;
}

+ (NSString *) getHtmlContext:(NSString *)newscontent
{
    if ([StringUtil isEmptyStr:newscontent]) {
        return newscontent;
    };
    NSRange range = [[newscontent lowercaseString] rangeOfString:CSTR_IMG_START];
    if(range.location == NSNotFound)
    {
        return newscontent;
    }
    NSMutableString *result = [[NSMutableString alloc] init];
    while (range.location != NSNotFound) {
        [result appendString:[newscontent substringToIndex:range.location]];
        newscontent = [newscontent substringFromIndex:range.location];
        range = [newscontent rangeOfString:CSTR_IMG_END];
        if(range.location==NSNotFound)
        {
            [result appendString:newscontent];
            break;
        }
        NSString *tmp = [newscontent substringToIndex:range.location];
        NSRange rangesrc = [[tmp lowercaseString] rangeOfString:CSTR_IMG_SRC];
        NSString *src=@"";
        if(rangesrc.location != NSNotFound)
        {
            tmp = [tmp substringFromIndex:rangesrc.location+rangesrc.length+1];
            
            NSRange range2 = [tmp rangeOfString:CSTR_IMG_REMOVE_END];
            
            if(range2.location != NSNotFound)
            {
                src = [tmp substringToIndex:range2.location];
            }
            else
                src = tmp;
            if(![StringUtil isEmptyStr:src])
            {
                if([[src lowercaseString] hasPrefix:[[DataInterfaceUtil getDataInterface:@"baseurl_pro"] lowercaseString]])
                {
                    src = [NSString stringWithFormat:@"%@%@",[DataInterfaceUtil getDataInterface:@"baseurl"],src];
                }
                [result appendFormat:@" <a href=\"%@\"> ",src];
                [result appendFormat:@"<center><img src=\"%@\" %@ /></center></a>",src,CSTR_IMG_INSERT];
                
            }
        }
        
        newscontent = [newscontent substringFromIndex:range.location+range.length];
        range = [[newscontent lowercaseString] rangeOfString:CSTR_IMG_START];
    }
    [result appendString:newscontent];
    return result;
}

+(NSString *)getHtmlSubTitle:(BaseVO *)kvo
{
    if(kvo == nil)
        return @"<div style=\"height:0;border-bottom:1px solid #f00\"></div>";
    return [kvo getHtmlSubTitle];
}

/**
 *是否是图片URL地址
 */
+ (BOOL) isImageUrl:(NSString *)url
{
    if([StringUtil isEmptyStr:url])
        return NO;
    NSString *lowcaseurl = [url lowercaseString];
    NSURL *tmpurl = [NSURL URLWithString:url];
    if(tmpurl!=nil
    &&([lowcaseurl hasSuffix:@".jpg"]||
       [lowcaseurl hasSuffix:@".png"]||
       [lowcaseurl hasSuffix:@".jpeg"]||
       [lowcaseurl hasSuffix:@".gif"]||
       [lowcaseurl hasSuffix:@".bmp"]))
        return YES;
    return NO;
}

//是否是重载链接
+(BOOL)isReloadLink:(NSString *)url
{
    if([StringUtil isEmptyStr:url])
        return NO;
    return [url isEqualToString:CSTR_RELOADLINK];
}

@end
