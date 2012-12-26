//html文本处理工具
//  HtmlUtil.h
//  Fellow
//
//  Created by wuzhu on 12-11-21.
//
//#import <Foundation/Foundation.h>

@interface HtmlUtil : NSObject

+ (NSString *) getHtmlHead;
+ (NSString *) getHtmlEnd;
+ (NSString *) getHtmlContext:(NSString *)newscontent;

+ (BOOL) isHtmlUrl:(NSString *)url;
@end
