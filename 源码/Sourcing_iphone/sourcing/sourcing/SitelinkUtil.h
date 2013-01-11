//
//  SitelinkUtil.h
//  Fellow
//
//  Created by wuzhu on 12-11-26.
//
//

#import <Foundation/Foundation.h>
#import "BaseServiceDelegate.h"

@interface SitelinkUtil : NSObject<BaseServiceDelegate>


/**
 *判断是否是站内新闻地址 如果是则返回新闻编号 如果不是返回nil
 */
+(NSString *) isSiteLink:(NSString *)url;

-(void) processSiteLink:(NSString *)newsid parentViewController:(UIViewController *)pvc;
@end
