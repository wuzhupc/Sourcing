//html文本处理工具
//  HtmlUtil.h
//  Fellow
//
//  Created by wuzhu on 12-11-21.
//
#import <Foundation/Foundation.h>
#import "BaseVO.h"

#define CSTR_RELOADLINK @"http://wuzhupc.com/reload"

@interface HtmlUtil : NSObject

+ (NSString *) getHtmlHead;
+ (NSString *) getHtmlEnd;
+ (NSString *) getHtmlContext:(NSString *)newscontent;

+ (NSString *)getHtmlErrorHit:(NSString *)kmsg hasreload:(BOOL)khas;

+(NSString *)getHtmlSubTitle:(BaseVO *)kvo;
+ (BOOL) isImageUrl:(NSString *)url;
+(BOOL)isReloadLink:(NSString *)url;
@end
